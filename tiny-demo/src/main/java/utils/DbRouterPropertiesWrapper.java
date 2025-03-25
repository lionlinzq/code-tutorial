package utils;

import conf.DbRouterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangning
 * @since 2019-04-23  17:51
 **/
@Component
public class DbRouterPropertiesWrapper {

    public static Long expireTime;
    public static Long cacheCleanupTime;
    public static Integer connectionMaxErrorNum;

    public static Long getExpireTime() {
        return expireTime;
    }

    @Value("${dbrouter.datasource.expire.time:1440}")
    public void setExpireTime(Long expireTime) {
        DbRouterPropertiesWrapper.expireTime = expireTime;
    }

    public static Long getCacheCleanupTime() {
        return cacheCleanupTime;
    }

    @Value("${dbrouter.cache.cleanup.time:1440}")
    public void setCacheCleanupTime(Long cacheCleanupTime) {
        DbRouterPropertiesWrapper.cacheCleanupTime = cacheCleanupTime;
    }

    public static Integer getConnectionMaxErrorNum() {
        return connectionMaxErrorNum;
    }

    @Value("${dbrouter.connection.max.error.num:5}")
    public void setConnectionMaxErrorNum(Integer connectionMaxErrorNum) {
        DbRouterPropertiesWrapper.connectionMaxErrorNum = connectionMaxErrorNum;
    }

    private static DbRouterProperties dbRouterProperties;

    public static DbRouterProperties getDbRouterProperties() {
        return dbRouterProperties;
    }

    @Autowired
    public void setDbRouterProperties(DbRouterProperties dbRouterProperties) {
        DbRouterPropertiesWrapper.dbRouterProperties = dbRouterProperties;
    }
}
