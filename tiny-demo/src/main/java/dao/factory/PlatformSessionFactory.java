package dao.factory;

import com.alibaba.druid.pool.DruidDataSource;
import conf.PlatformDataSourceProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import utils.Delimiters;
import utils.ListUtil;
import utils.PoolDataSourceConfig;
import utils.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据源的工厂类
 *
 * @author rongdi
 * @Date 2016年12月2日
 * @Version 1.0.0
 */
@Component
public class PlatformSessionFactory {
    private static final Logger logger = LoggerFactory.getLogger(PlatformSessionFactory.class);

    @Autowired
    PlatformDataSourceProperties properties;

    // 扫描路径
    private String scanLocation;

    public void appendScanLocation(String scanLocation) {
        if(StringUtils.isBlank(this.scanLocation)) {
            this.scanLocation = scanLocation;
        } else  if(this.scanLocation.endsWith(";")) {
            this.scanLocation += scanLocation;
        } else {
            this.scanLocation += ";" + scanLocation;
        }
    }

    //主库的sessionFactory集合
    private List<SqlSessionFactory> masterFactorys = new ArrayList<SqlSessionFactory>();
    //从库的sessionFactory集合
    private List<SqlSessionFactory> slaveFactorys = new ArrayList<SqlSessionFactory>();
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 资源
    private static Resource[] resources;

    /**
     * 获取单个SqlSessionFactory
     *
     * @param type 类型：读、写、读写
     * @return
     */
    public SqlSessionFactory getSinggleSessionFactory(PoolDataSourceConfig.PoolType type) {
        SqlSessionFactory factory = null;

        int masterNum = properties.getMasterDriverClassName().length;
        int slaveNum = properties.getSlaveDriverClassName().length;
        if (properties.getMode() == 1) {
            //主主模式
            if (ListUtil.isBlank(masterFactorys)) {
                buildMasterDruidSessionFacotry();
            }

            switch (type) {
                case READ:
                case WRITE:
                default:
                    factory = masterFactorys.get(new Random().nextInt(masterNum));
                    break;
            }
        } else {
            //主从模式

            if (ListUtil.isBlank(masterFactorys)) {
                buildMasterDruidSessionFacotry();
            }

            if (ListUtil.isBlank(slaveFactorys)) {
                buildSlaverDruidSessionFacotry();
            }

            switch (type) {
                case READ:
                    int index = new Random().nextInt(slaveNum);
                    factory = slaveFactorys.get(index);
                    break;
                case WRITE:
                    factory = masterFactorys.get(0);
                    break;
                default:
                    factory = masterFactorys.get(0);
                    break;
            }

        }
        return factory;
    }

    /**
     * 构建主库的SqlSessionFactory
     */
    private synchronized void buildMasterDruidSessionFacotry() {
        String[] masterDriverClassName = properties.getMasterDriverClassName();
        String[] masterUrl = properties.getMasterUrl();
        String[] masterUsername = properties.getMasterUsername();
        String[] masterPassword = properties.getMasterPassword();
        try {
            for (int i = 0; i < masterDriverClassName.length; i++) {
                //主sessionFactory
                SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory(masterDriverClassName[i], masterUrl[i], masterUsername[i], masterPassword[i]);
                masterFactorys.add(sqlSessionFactory);
            }
        } catch (Exception e) {
            logger.error("buildMasterDruidSessionFacotry error ", e);
        }
    }

    /**
     * 构建从库的SqlSessionFactory
     */
    private synchronized void buildSlaverDruidSessionFacotry() {
        String[] slaveDriverClassName = properties.getSlaveDriverClassName();
        String[] slaveUrl = properties.getSlaveUrl();
        String[] slaveUsername = properties.getSlaveUsername();
        String[] slavePassword = properties.getSlavePassword();
        try {
            for (int i = 0; i < slaveDriverClassName.length; i++) {
                //从sessionFactory
                SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory(slaveDriverClassName[i], slaveUrl[i], slaveUsername[i], slavePassword[i]);
                slaveFactorys.add(sqlSessionFactory);
            }
        } catch (Exception e) {
            logger.error("buildSlaverDruidSessionFacotry error ", e);
        }
    }

    /**
     * 构建SqlSessionFactory
     *
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    private SqlSessionFactory buildSqlSessionFactory(String driverClassName, String url, String username, String password) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestWhileIdle(properties.getTestWhileIdle());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());
        dataSource.setValidationQuery(properties.getValidationQuery());
        dataSource.setTestOnReturn(properties.getTestOnReturn());
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMaxWait(properties.getMaxWait());
        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        dataSource.setMinIdle(properties.getMinIdle());

        if (StringUtils.isNotEmpty(properties.getFilters())) {
            dataSource.setFilters(properties.getFilters());
        }

        // 连接属性，示例数据：druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        if (StringUtils.isNotEmpty(properties.getConnectionProperties())) {
            String connectionProperties = properties.getConnectionProperties();
            String[] pros = connectionProperties.split(Delimiters.SICOLON);
            Properties p = new Properties();
            for (String pro : pros) {
                String[] ss = pro.split("=");
                p.setProperty(ss[0], ss[1]);
            }
            dataSource.setConnectProperties(p);
        }

        if (properties.getUseGlobalDataSourceStat() != null) {
            dataSource.setUseGlobalDataSourceStat(properties.getUseGlobalDataSourceStat());
        }

        // 扫描路径下的文件
        if (resources == null) {
            resources = getResources();
        }

        // MyBatis配置路径
        //  Resource mybatisConfigResource = PropertiesUtils.getConfigResources();

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTransactionFactory(new JdbcTransactionFactory());
        sessionFactoryBean.setMapperLocations(resources);
        // sessionFactoryBean.setConfigLocation(mybatisConfigResource);//解决字段为null值，导致map里连key都没有的问题
        Configuration configuration = new Configuration();
        configuration.setCallSettersOnNulls(true);
        sessionFactoryBean.setConfiguration(configuration);

        return sessionFactoryBean.getObject();
    }

    private Resource[] getResources() throws IOException {

        // core包的扫描资源
        String coreScanLocation = "classpath:com/xuanwu/apaas/core/dao/mapper/**/*-mapper.xml;";
        Resource[] coreResources = ResourceUtils.getResources(coreScanLocation);

        if (scanLocation != null) {
            Resource[] appResources = ResourceUtils.getResources(scanLocation);

            resources = ArrayUtils.addAll(coreResources, appResources);
        } else {
            resources = coreResources;
        }

        return resources;
    }
}