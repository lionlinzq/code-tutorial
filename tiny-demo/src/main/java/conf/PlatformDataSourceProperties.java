package conf;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = PlatformDataSourceProperties.PREFIX)
public class PlatformDataSourceProperties implements BeanClassLoaderAware {

    public static final String PREFIX = "xw.platform";

    private ClassLoader classLoader;
    private String scanLocation;
    private Integer maxActive;
    private Integer mode;
    private Integer initialSize;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minIdle;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean poolPreparedStatements = false;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private Boolean useGlobalDataSourceStat;
    private String[] masterDriverClassName = {"org.postgresql.Driver"};
    private String[] masterUrl;
    private String[] masterUsername;
    private String[] masterPassword;
    private String[] slaveDriverClassName = {"org.postgresql.Driver"};
    private String[] slaveUrl;
    private String[] slaveUsername;
    private String[] slavePassword;


    /**
     * @return the classLoader
     */
    public ClassLoader getClassLoader() {
        return classLoader;
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;

    }

    public String getScanLocation() {
        return scanLocation;
    }

    public void setScanLocation(String scanLocation) {
        this.scanLocation = scanLocation;
    }

    public String[] getMasterDriverClassName() {
        return masterDriverClassName;
    }

    public void setMasterDriverClassName(String[] masterDriverClassName) {
        this.masterDriverClassName = masterDriverClassName;
    }

    public String[] getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String[] masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String[] getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String[] masterUsername) {
        this.masterUsername = masterUsername;
    }

    public String[] getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String[] masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String[] getSlaveDriverClassName() {
        return slaveDriverClassName;
    }

    public void setSlaveDriverClassName(String[] slaveDriverClassName) {
        this.slaveDriverClassName = slaveDriverClassName;
    }

    public String[] getSlaveUrl() {
        return slaveUrl;
    }

    public void setSlaveUrl(String[] slaveUrl) {
        this.slaveUrl = slaveUrl;
    }

    public String[] getSlaveUsername() {
        return slaveUsername;
    }

    public void setSlaveUsername(String[] slaveUsername) {
        this.slaveUsername = slaveUsername;
    }

    public String[] getSlavePassword() {
        return slavePassword;
    }

    public void setSlavePassword(String[] slavePassword) {
        this.slavePassword = slavePassword;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public Boolean getUseGlobalDataSourceStat() {
        return useGlobalDataSourceStat;
    }

    public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
        this.useGlobalDataSourceStat = useGlobalDataSourceStat;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

}