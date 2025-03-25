package service.impl;


import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.xuanwu.apaas.core._enum.TenantDBType;
import com.xuanwu.apaas.core.conf.DbRouterProperties;
import com.xuanwu.apaas.core.dao.Operate;
import com.xuanwu.apaas.core.dao.factory.PlatformOperateFactory;
import com.xuanwu.apaas.core.domain.TenantDB;
import com.xuanwu.apaas.core.service.IDataSourceService;
import com.xuanwu.apaas.core.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IDataSourceServiceImpl implements IDataSourceService {
    private final static Logger logger = LoggerFactory.getLogger(IDataSourceServiceImpl.class);

    private static final int UNKNOWN_TRANSACTIONISOLATION = -1;
    public static Cache<String, DataSource> cache;
    public final static String CACHE_KEY_SEPARATOR = "__";

    // 租户数据库的连接属性
    private DbRouterProperties dataSourceConfig;

    private final static byte[] SECURITY_KEY = {0x36, 0x31, 0x33, 0x30, 0x32, 0x32, 0x32, 0x32, 0x32};
    private final static String ENCODE_URL_PREFIX = "econ://";

    /**
     * 解密连接串方法，兼容非加密的连接串
     * 如果连接串以econ://开头的表示加密串，返回解密后的结果；
     * 否则，原样返回
     * @param eurl
     * @return
     */
    private static String decodeUrl(String eurl) {
        if(eurl.startsWith(ENCODE_URL_PREFIX)) {
            String eStr = eurl.substring(7);
            try {
                return DesUtil.decrypt(eStr, new String(SECURITY_KEY));
            } catch (Exception e) {
                logger.error("解密加密链接串失败", e);
                throw new RuntimeException("解密加密链接串失败", e);
            }
        } else {
            return eurl;
        }
    }

    public IDataSourceServiceImpl() {
        logger.debug("IDataSourceServiceImpl initialization ......");
        dataSourceConfig = DbRouterPropertiesWrapper.getDbRouterProperties();

        Long expireTime = DbRouterPropertiesWrapper.getExpireTime();
        Long cleanupTime = DbRouterPropertiesWrapper.getCacheCleanupTime();

        // 初始化缓存
        cache = CacheBuilder.newBuilder()
                .removalListener(new RemoveCacheListener())
                .expireAfterAccess(expireTime, TimeUnit.MINUTES)
                .build();

        // 开启缓存清理线程
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(() -> {
            cache.cleanUp();
        }, cleanupTime, cleanupTime, TimeUnit.MINUTES);
    }

    public DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception {
        String dataSourcePool = dataSourceConfig.getDataSource();
        logger.info("tenantKeyParams:" + tenantCode + "|" + tenantDBType + "|" + productCode);
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode);
        logger.info("tenantKey:" + tenantKey);
        return cache.get(tenantKey, () -> {
            if (!StringUtil.hasLength(dataSourcePool)) {
                return new DisPoolDataSource(tenantCode, tenantDBType, productCode);
            } else {
                return initRouterInfo(tenantCode, tenantDBType, productCode);
            }
        });
    }


    public DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) throws Exception {
        String dataSourcePool = dataSourceConfig.getDataSource();
        logger.info("tenantKeyParams2:" + tenantCode + "|" + tenantDBType + "|" + productCode + "|" + tenantDbName);
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode, tenantDbName);
        logger.info("tenantKey2:" + tenantKey);
        return cache.get(tenantKey, () -> {
            if (!StringUtil.hasLength(dataSourcePool)) {
                return new DisPoolDataSource(tenantCode, tenantDBType, productCode, tenantDbName);
            } else {
                return initRouterInfo(tenantCode, tenantDBType, productCode, tenantDbName);
            }
        });
    }

    private DataSource initRouterInfo(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) throws Exception {
        TenantDB dbInfo = getDBinfo(tenantCode, tenantDBType, productCode, tenantDbName);
        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        Class<?> dataSourceClass = Class.forName(dataSourceConfig.getDataSource());
        // 对连接串中的%做转义处理
        String connstr = decodeUrl(dbInfo.getConnstr()).replaceAll("%", "%25");
        dbInfo.setConnstr(connstr);
        Object obj = dataSourceClass.newInstance();
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode, tenantDbName);
        Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new DataSourceProxy((DataSource) obj, tenantKey));
        this.config(obj, dbInfo);
        return (DataSource) proxyObj;
    }


    public DataSource getDataSource(Long tenantCode, Long tenantDbCode) throws Exception {
        String dataSourcePool = dataSourceConfig.getDataSource();
        TenantDB dbInfo = getDBinfo(tenantCode, tenantDbCode);
        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        if (!StringUtil.hasLength(dataSourcePool)) {
            return new DisPoolDataSource(tenantCode,tenantDbCode);
        } else {
            return initRouterInfo(tenantCode,tenantDbCode);
        }
    }

    private TenantDB getDBinfo(long tenantCode, Long tenantdbcode) {
        Operate operate = PlatformOperateFactory.createReadableOperate();
        ParamMap params = ParamMap.getInstance()
                .add("tenantcode", String.valueOf(tenantCode))
                .add("tenantdbcode", String.valueOf(tenantdbcode));

        TenantDB tenantDB = null;
        try {
            tenantDB = operate.singleResult("selectReportTenant", TenantDB.class, params);
            //如需解密则解密
            String connstr = tenantDB.getConnstr();
            if(!connstr.startsWith("jdbc:")){
                tenantDB.setConnstr(DesUtil.decrypt(connstr, "613022222"));
            }
        } catch (Exception e) {
            logger.error("查询租户的DB信息失败！", e);
        }

        return tenantDB;
    }


    public void reloadSingleDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception {
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode);
        cache.invalidate(tenantKey);
        DataSource dataSource = initRouterInfo(tenantCode, tenantDBType, productCode);
        cache.put(tenantKey, dataSource);
    }

    public Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode,Long tenantDbCode) throws SQLException {
        TenantDB dbInfo = null;
        if(tenantDbCode != null && tenantDbCode != 0L){
            dbInfo = getDBinfo(tenantCode,tenantDbCode);
        }else{
           dbInfo = getDBinfo(tenantCode, tenantDBType, productCode);
        }

        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        String connstr = decodeUrl(dbInfo.getConnstr());
        return DriverManager.getConnection(connstr);
    }


    public Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode, Long tenantDbCode, String tenantDbName) throws SQLException {
        TenantDB dbInfo = null;
        if(tenantDbCode != null && tenantDbCode != 0L){
            dbInfo = getDBinfo(tenantCode,tenantDbCode);
        }else{
           dbInfo = getDBinfo(tenantCode, tenantDBType, productCode, tenantDbName);
        }

        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        String connstr = decodeUrl(dbInfo.getConnstr());
        return DriverManager.getConnection(connstr);
    }

    private TenantDB getDBinfo(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) {
        Operate operate = PlatformOperateFactory.createReadableOperate();
        ParamMap params = ParamMap.getInstance()
                .add("tenantcode", String.valueOf(tenantCode))
                .add("tenantdbtype", String.valueOf(tenantDBType.getDBType()))
                .add("productcode", String.valueOf(productCode))
                .add("tenantDbName", tenantDbName);

        TenantDB tenantDB = null;
        try {
            tenantDB = operate.singleResult("selectTenant2", TenantDB.class, params);
            //如需解密则解密
            String connstr = tenantDB.getConnstr();
            if(!connstr.startsWith("jdbc:")){
                tenantDB.setConnstr(DesUtil.decrypt(connstr, "613022222"));
            }
        } catch (Exception e) {
            logger.error("查询租户的DB信息失败！", e);
        }
        return tenantDB;
    }


    public List<Map<String, String>> listAll() {
        List<Map<String, String>> list = new ArrayList<>();

        ConcurrentMap<String, DataSource> map = cache.asMap();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String[] vals = key.split(CACHE_KEY_SEPARATOR);
            Map<String, String> valMap = new HashMap<>();
            valMap.put("tecode", vals[0]);
            valMap.put("type", String.valueOf(TenantDBType.getDBType(vals[1]).getDBType()));
            valMap.put("pdcode", vals[2]);
            DataSource dataSource = map.get(key);
            String dsType = dataSourceConfig.getDataSource();
            if (StringUtil.hasLength(dsType)) {
                valMap.put("datasourcetype", dsType);
            } else {
                valMap.put("datasourcetype", dataSource.getClass().getName());
            }
            try {
                Method getUrl = dataSource.getClass().getMethod("getUrl");
                String url = (String) getUrl.invoke(dataSource);
                valMap.put("url", url);
            } catch (ReflectiveOperationException e) {
                valMap.put("url", "获取失败或无url");
                e.printStackTrace();
            }
            list.add(valMap);
        }
        return list;
    }


    public void reloadAll() {

    }

    public void clearAll() {
        cache.invalidateAll();
    }

    public void clear(Long tenantCode, TenantDBType tenantDBType, Long productCode) {
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode);
        cache.invalidate(tenantKey);
    }

    /**
     * 初始化数据库路由
     *
     * @param tenantCode   租户数据库
     * @param tenantDbCode 租户数据库编码
     */
    private DataSource initRouterInfo(Long tenantCode, Long tenantDbCode) throws Exception {
        TenantDB dbInfo = getDBinfo(tenantCode,tenantDbCode);
        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        Class<?> dataSourceClass = Class.forName(dataSourceConfig.getDataSource());
        // 对连接串中的%做转义处理
        String connstr = decodeUrl(dbInfo.getConnstr()).replaceAll("%", "%25");
        dbInfo.setConnstr(connstr);
        Object obj = dataSourceClass.newInstance();
        String tenantKey = genCacheKey(tenantCode,tenantDbCode);
        Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new DataSourceProxy((DataSource) obj, tenantKey));
        this.config(obj, dbInfo);
        return (DataSource) proxyObj;
    }



    /**
     * 初始化数据库路由
     *
     * @param tenantCode   租户数据库
     * @param tenantDBType 租户数据库类型
     * @param productCode  行业编码
     */
    private DataSource initRouterInfo(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception {
        TenantDB dbInfo = getDBinfo(tenantCode, tenantDBType, productCode);
        if (dbInfo == null) {
            throw new RuntimeException("没有该租户数据源！");
        }
        Class<?> dataSourceClass = Class.forName(dataSourceConfig.getDataSource());
        // 对连接串中的%做转义处理
        String connstr = decodeUrl(dbInfo.getConnstr()).replaceAll("%", "%25");
        dbInfo.setConnstr(connstr);
        Object obj = dataSourceClass.newInstance();
        String tenantKey = genCacheKey(tenantCode, tenantDBType, productCode);
        Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new DataSourceProxy((DataSource) obj, tenantKey));
        this.config(obj, dbInfo);
        return (DataSource) proxyObj;
    }


    /**
     * 获取租户数据库的信息
     *
     * @param tenantCode   租户数据库
     * @param tenantDBType 租户数据库类型
     * @param productCode  行业编码
     * @return 租户数据库信息
     */
    private TenantDB getDBinfo(Long tenantCode, TenantDBType tenantDBType, Long productCode) {
        Operate operate = PlatformOperateFactory.createReadableOperate();
        ParamMap params = ParamMap.getInstance()
                .add("tenantcode", String.valueOf(tenantCode))
                .add("tenantdbtype", String.valueOf(tenantDBType.getDBType()))
                .add("productcode", String.valueOf(productCode));


        TenantDB tenantDB = null;
        try {
            tenantDB = operate.singleResult("selectTenant", TenantDB.class, params);
            //如需解密则解密
            String connstr = tenantDB.getConnstr();
            if(!connstr.startsWith("jdbc:")){
                tenantDB.setConnstr(DesUtil.decrypt(connstr, "613022222"));
            }
        } catch (Exception e) {
            logger.error("查询租户的DB信息失败！", e);
        }

        return tenantDB;
    }

    /**
     * @param tenantCode
     * @param tenantDBType
     * @param productCode
     * @return
     */
    private String genCacheKey(Long tenantCode, TenantDBType tenantDBType, Long productCode) {
        //兼容旧版ide保存业务confjson时tenantdbname为空的时导致的数据源引用不一致导致的事务问题
        switch (tenantDBType.getDBType()){
            case 1:
                return genCacheKey(tenantCode, tenantDBType, productCode, "只读");
            case 2:
                return genCacheKey(tenantCode, tenantDBType, productCode, "读写");
            case 3:
                return genCacheKey(tenantCode, tenantDBType, productCode, "报表");
        }
        return tenantCode + CACHE_KEY_SEPARATOR + tenantDBType + CACHE_KEY_SEPARATOR + productCode;
    }

    private String genCacheKey(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) {
        return tenantCode + CACHE_KEY_SEPARATOR + tenantDBType + CACHE_KEY_SEPARATOR + productCode + CACHE_KEY_SEPARATOR + tenantDbName;
    }

    private String genCacheKey(Long tenantCode, Long tenantDbCode) {
        return tenantCode + CACHE_KEY_SEPARATOR + tenantDbCode;

    }

    /**
     * 设置对象的属性值
     *
     * @param obj       对象
     * @param fieldName 对象的属性名称
     * @param clz       对应方法的参数类型
     * @param value     设置的值
     * @throws Exception
     */
    public void setObjectValue(Object obj, String fieldName, Class<?> clz, Object value) throws Exception {
        String firstChar = fieldName.substring(0, 1).toUpperCase();
        String methodName = String.format("set%s%s", firstChar, fieldName.substring(1));
        Method method = obj.getClass().getMethod(methodName, clz);
        method.setAccessible(true);
        method.invoke(obj, value);
    }


    /**
     * 使用反射配置数据源参数
     *
     * @param obj    数据源对象
     * @param DBInfo 租户数据库信息对象
     * @throws Exception
     */
    public void config(Object obj, TenantDB DBInfo) throws Exception {
        String value = null;

        Integer initialSize = dataSourceConfig.getInitialSize();
        if (initialSize != null) {
            this.setObjectValue(obj, "initialSize", int.class, initialSize);
        }

        Integer maxActive = dataSourceConfig.getMaxActive();
        if (StringUtil.isNotNull(maxActive)) {
            this.setObjectValue(obj, "maxActive", int.class, maxActive);
        }

        Integer maxIdle = dataSourceConfig.getMaxIdle();
        if (StringUtil.isNotNull(maxIdle)) {
            this.setObjectValue(obj, "maxIdle", int.class, maxIdle);
        }

        Integer maxWait = dataSourceConfig.getMaxWait();
        if (StringUtil.isNotNull(maxWait)) {
            this.setObjectValue(obj, "maxWait", long.class, maxWait);
        }

        Integer minIdle = dataSourceConfig.getMinIdle();
        if (minIdle != null) {
            this.setObjectValue(obj, "minIdle", int.class, minIdle);
        }

        Integer numTestsPerEvictionRun = dataSourceConfig.getNumTestsPerEvictionRun();
        if (numTestsPerEvictionRun != null) {
            this.setObjectValue(obj, "numTestsPerEvictionRun", int.class, numTestsPerEvictionRun);
        }

        Integer validationQueryTimeout = dataSourceConfig.getValidationQueryTimeout();
        if (validationQueryTimeout != null) {
            this.setObjectValue(obj, "validationQueryTimeout", int.class, validationQueryTimeout);
        }

        Boolean testOnBorrow = dataSourceConfig.getTestOnBorrow();
        if (testOnBorrow != null) {
            this.setObjectValue(obj, "testOnBorrow", boolean.class, testOnBorrow);
        }

        Boolean testOnReturn = dataSourceConfig.getTestOnReturn();
        if (testOnReturn != null) {
            this.setObjectValue(obj, "testOnReturn", boolean.class, testOnReturn);
        }

        Boolean testWhileIdle = dataSourceConfig.getTestWhileIdle();
        if (testWhileIdle != null) {
            this.setObjectValue(obj, "testWhileIdle", boolean.class, testWhileIdle);
        }

        Boolean defaultAutoCommit = dataSourceConfig.getDefaultAutoCommit();
        if (defaultAutoCommit != null) {
            this.setObjectValue(obj, "defaultAutoCommit", boolean.class, defaultAutoCommit);
        }

        Boolean defaultReadOnly = dataSourceConfig.getDefaultReadOnly();
        if (defaultReadOnly != null) {
            this.setObjectValue(obj, "defaultReadOnly", boolean.class, defaultReadOnly);
        }

        Boolean accessToUnderlyingConnectionAllowed = dataSourceConfig.getAccessToUnderlyingConnectionAllowed();
        if (accessToUnderlyingConnectionAllowed != null) {
            this.setObjectValue(obj, "accessToUnderlyingConnectionAllowed", boolean.class, accessToUnderlyingConnectionAllowed);
        }

        Boolean removeAbandoned = dataSourceConfig.getRemoveAbandoned();
        if (removeAbandoned != null) {
            this.setObjectValue(obj, "removeAbandoned", boolean.class, removeAbandoned);
        }

        Long timeBetweenEvictionRunsMillis = dataSourceConfig.getTimeBetweenEvictionRunsMillis();
        if (timeBetweenEvictionRunsMillis != null) {
            this.setObjectValue(obj, "timeBetweenEvictionRunsMillis", long.class, timeBetweenEvictionRunsMillis);
        }

        Long minEvictableIdleTimeMillis = dataSourceConfig.getMinEvictableIdleTimeMillis();
        if (minEvictableIdleTimeMillis != null) {
            this.setObjectValue(obj, "minEvictableIdleTimeMillis", long.class, minEvictableIdleTimeMillis);
        }

        Long phyTimeoutMillis = dataSourceConfig.getPhyTimeoutMillis();
        if (phyTimeoutMillis != null) {
            this.setObjectValue(obj, "phyTimeoutMillis", long.class, phyTimeoutMillis);
        }

        value = dataSourceConfig.getDriverClassName();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "driverClassName", String.class, value);
        } else {
            throw new RuntimeException("找不到驱动！");
        }


        value = decodeUrl(DBInfo.getConnstr());  // 从 租户信息中获取连接串
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "url", String.class, value);
        } else {
            throw new RuntimeException("该租户没有添加数据库！");
        }


        value = dataSourceConfig.getDefaultCatalog();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "defaultCatalog", String.class, value);
        }

        value = dataSourceConfig.getPassword();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "password", String.class, value);
        }

        value = dataSourceConfig.getUsername();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "username", String.class, value);
        }

        value = dataSourceConfig.getValidationQuery();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "validationQuery", String.class, value);
        }


        Integer removeAbandonedTimeout = dataSourceConfig.getRemoveAbandonedTimeout();
        if (removeAbandonedTimeout != null) {
            this.setObjectValue(obj, "removeAbandonedTimeout", int.class, removeAbandonedTimeout);
        }


        Boolean logAbandoned = dataSourceConfig.getLogAbandoned();
        if (logAbandoned != null) {
            this.setObjectValue(obj, "logAbandoned", boolean.class, logAbandoned);
        }

        value = dataSourceConfig.getFilters();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "filters", String.class, value);
        }
        value = dataSourceConfig.getExceptionSorter();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "exceptionSorter", String.class, value);
        }
        value = dataSourceConfig.getExceptionSorter();
        if (StringUtil.hasLength(value)) {
            this.setObjectValue(obj, "exceptionSorter", String.class, value);
        }

        Boolean poolPreparedStatements = dataSourceConfig.getPoolPreparedStatements();
        if (poolPreparedStatements != null) {
            this.setObjectValue(obj, "poolPreparedStatements", boolean.class, poolPreparedStatements);
            if (poolPreparedStatements) {
                Integer maxOpenPreparedStatements = dataSourceConfig.getMaxOpenPreparedStatements();
                if (maxOpenPreparedStatements != null) {
                    this.setObjectValue(obj, "maxOpenPreparedStatements", int.class, maxOpenPreparedStatements);
                }
            }
        }

        value = dataSourceConfig.getDefaultTransactionIsolation();
        if (StringUtil.hasLength(value)) {
            int level = UNKNOWN_TRANSACTIONISOLATION;
            if ("NONE".equalsIgnoreCase(value)) {
                level = Connection.TRANSACTION_NONE;
            } else if ("READ_COMMITTED".equalsIgnoreCase(value)) {
                level = Connection.TRANSACTION_READ_COMMITTED;
            } else if ("READ_UNCOMMITTED".equalsIgnoreCase(value)) {
                level = Connection.TRANSACTION_READ_UNCOMMITTED;
            } else if ("REPEATABLE_READ".equalsIgnoreCase(value)) {
                level = Connection.TRANSACTION_REPEATABLE_READ;
            } else if ("SERIALIZABLE".equalsIgnoreCase(value)) {
                level = Connection.TRANSACTION_SERIALIZABLE;
            } else {
                try {
                    level = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    level = UNKNOWN_TRANSACTIONISOLATION;
                }
            }
            this.setObjectValue(obj, "defaultTransactionIsolation", String.class, level);
        }

        value = dataSourceConfig.getConnectionProperties();
        if (StringUtil.hasLength(value)) {
            String[] entries = value.split(";");
            Properties properties = new Properties();
            for (int i = 0; i < entries.length; i++) {
                String entry = entries[i];
                if (entry.length() > 0) {
                    int index = entry.indexOf('=');
                    if (index > 0) {
                        String name = entry.substring(0, index);
                        String oneOfValue = entry.substring(index + 1);
                        properties.setProperty(name, oneOfValue);
                    } else {
                        // no value is empty string which is how java.util.Properties works
                        properties.setProperty(entry, "");
                    }
                }
            }
            this.setObjectValue(obj, "connectProperties", Properties.class, properties);
        }
    }

    /**
     * 静态内部类去监听当数据源缓存被清除后的清理工作
     */
    static class RemoveCacheListener implements RemovalListener<String, DataSource> {

        @Override
        public void onRemoval(RemovalNotification<String, DataSource> notification) {
            System.out.println("+++++++++++++++++++++++++++销毁销毁+++++++++++++++++");
            DataSource value = notification.getValue();
            try {
                Method close = value.getClass().getMethod("close");
                close.invoke(value);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据源关闭异常！");
            }
        }
    }

    private class DataSourceProxy implements InvocationHandler {

        private DataSource dataSource;
        private String key;
        private AtomicInteger errorCount = new AtomicInteger();
        private Integer maxErrorNum = DbRouterPropertiesWrapper.getConnectionMaxErrorNum();

        public DataSourceProxy(DataSource dataSource, String key) {
            this.dataSource = dataSource;
            this.key = key;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("getConnection".equals(method.getName())) {
                try {
                    logger.info("当前代理的数据源：" + ((DruidDataSource) dataSource).getUrl());
                    Object obj = method.invoke(dataSource, args);
                    logger.info("当前连接：" + obj);
                    errorCount.set(0);
                    return obj;
                } catch (Exception e) {
                    synchronized (this) {
                        if (errorCount.getAndIncrement() >= maxErrorNum) {
                            cache.invalidate(key);
                        }
                        String[] val = key.split(CACHE_KEY_SEPARATOR);
                        throw new RuntimeException("租户：" + val[0] + ",第[" + errorCount.get() + "]获取连接失败!", e);
                    }
                }
            } else {
                return method.invoke(dataSource, args);
            }
        }
    }

}


