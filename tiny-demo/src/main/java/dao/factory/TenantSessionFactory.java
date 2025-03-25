package dao.factory;

import _enum.TenantDBType;
import conf.DbRouterProperties;
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
import utils.DataSourceRouterUtil;
import utils.DataSourceUtils;
import utils.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

;

/**
 * 数据源的工厂类
 *
 * @author rongdi
 * @Date 2016年12月2日
 * @Version 1.0.0
 */
@Component
public class TenantSessionFactory {
    private static final Logger logger = LoggerFactory.getLogger(TenantSessionFactory.class);

    @Autowired
    DbRouterProperties properties;

    private String scanLocation;
    private final ConcurrentHashMap<String, SqlSessionFactory> sqlSessionFactoryCache = new ConcurrentHashMap<>();
    private static final TenantDataSource tenantDataSource = new TenantDataSource();
    private static Resource[] resources;

    public void appendScanLocation(String scanLocation) {
        if (StringUtils.isBlank(this.scanLocation)) {
            this.scanLocation = scanLocation;
        } else if (this.scanLocation.endsWith(";")) {
            this.scanLocation += scanLocation;
        } else {
            this.scanLocation += ";" + scanLocation;
        }
    }

    public void setTenantSouceCurrentKey(String key) {
        logger.debug("更换当前数据源的链接key:{}",key);
        tenantDataSource.setKey(key);
    }

    public SqlSessionFactory getTenantDruidSessionFactory(Long tenantCode, Long productCode, TenantDBType dbType) {
        return getTenantDruidSessionFactory(tenantCode, productCode, dbType, null);
    }

    public SqlSessionFactory getTenantDruidSessionFactory(Long tenantCode, Long productCode, TenantDBType dbType, String dbName) {
        String key = dbName == null ?
                DataSourceUtils.tran2Key(tenantCode, productCode, dbType) :
                DataSourceUtils.tran2Key(tenantCode, productCode, dbType, dbName);

        DataSource dataSource = null;
        try {
            dataSource = dbName == null ?
                    DataSourceRouterUtil.getDataSource(tenantCode, dbType, productCode) :
                    DataSourceRouterUtil.getDataSource(tenantCode, dbType, productCode, dbName);
        } catch (Exception e) {
            logger.error("Get enterprise:{} datasource failed cause:{}", tenantCode, e);
            throw new RuntimeException(e);
        }

        tenantDataSource.addDataSouce(key, dataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryCache.computeIfAbsent(key, k -> {
            try {


                if (resources == null) {
                    loadResources();
                }

                SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
                sessionFactoryBean.setDataSource(tenantDataSource);
                sessionFactoryBean.setTransactionFactory(new JdbcTransactionFactory());
                sessionFactoryBean.setMapperLocations(resources);

                Configuration configuration = new Configuration();
                configuration.setCallSettersOnNulls(true);
                sessionFactoryBean.setConfiguration(configuration);

                return sessionFactoryBean.getObject();
            } catch (Exception e) {
                logger.error("Get enterprise:{} session factory failed cause:{}", tenantCode, e);
                throw new RuntimeException(e);
            }
        });

        return sqlSessionFactory;
    }

    private synchronized void loadResources() throws IOException {
        if (resources == null && scanLocation != null) {
            resources = ResourceUtils.getResources(scanLocation);
        }
    }

}
