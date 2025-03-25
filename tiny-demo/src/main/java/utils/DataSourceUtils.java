package utils;


import _enum.TenantDBType;
import domain.DataSourceAddress;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangning
 * @since 2019-04-12  09:51
 **/
public class DataSourceUtils {

    /**
     * @param address
     * @param poolType
     * @return
     */
    public static PoolDataSourceConfig createPoolDataSourceConfig(String[] address, PoolDataSourceConfig.PoolType poolType) {
        PoolDataSourceConfig config = new PoolDataSourceConfig();
        config.setMaxActive(5);
        config.setUrl(address[0]);
        config.setPoolType(poolType);
        config.setValidationQueryTimeout(15);
        config.setUserName(address[1].replace("userid=", ""));
        config.setPassword(address[2].replace("password=", ""));
        setDriverClassName(config.getUrl(), config);

        String validationQuery = "select 1";
        if (address.length > 3) {
            if (StringUtils.isNotBlank(address[3])) {
                validationQuery = address[3];
            }
        }
        config.setValidationQuery(validationQuery);

        return config;
    }

    /**
     * @param jdbcUrl
     * @param config
     */
    private static void setDriverClassName(String jdbcUrl, PoolDataSourceConfig config) {
        if (jdbcUrl.indexOf(DataBaseType.Postgresql.getDriverURL()) != -1) {
            config.setDbType(DataBaseType.Postgresql);
            config.setDriverClassName(DataBaseType.Postgresql.getDriverName());
        } else if (jdbcUrl.indexOf(DataBaseType.SqlServer.getDriverURL()) != -1) {
            config.setDbType(DataBaseType.SqlServer);
            config.setDriverClassName(DataBaseType.SqlServer.getDriverName());
            if (!jdbcUrl.contains("DatabaseName")) {
                String[] urls = jdbcUrl.split("/");
                config.setUrl(urls[0] + "//" + urls[2] + ";DatabaseName="
                        + urls[3]);
            }
        } else if (jdbcUrl.indexOf(DataBaseType.SqlServer2000.getDriverURL()) != -1) {
            config.setDbType(DataBaseType.SqlServer2000);
            config.setDriverClassName(DataBaseType.SqlServer2000
                    .getDriverName());
            if (!jdbcUrl.contains("DatabaseName")) {
                String[] urls = jdbcUrl.split("/");
                config.setUrl(urls[0] + "//" + urls[2] + ";DatabaseName="
                        + urls[3]);
            }
        } else if (jdbcUrl.indexOf(DataBaseType.Oracle.getDriverURL()) != -1) {
            config.setDbType(DataBaseType.Oracle);
            config.setDriverClassName(DataBaseType.Oracle.getDriverName());
        } else if (jdbcUrl.indexOf(DataBaseType.Mysql.getDriverURL()) != -1) {
            config.setDbType(DataBaseType.Mysql);
            config.setDriverClassName(DataBaseType.Mysql.getDriverName());
        } else {
            throw new IllegalArgumentException("Illegal jdbc url:" + jdbcUrl);
        }
    }

    /**
     * @param address
     * @return
     */
    public static List<PoolDataSourceConfig> createPoolDataSourceConfigList(DataSourceAddress address) {
        List<PoolDataSourceConfig> configs = new ArrayList<PoolDataSourceConfig>();

        String[] addressArr = address.getAddress().split("\\|\\|");
        String[] writeAddress = addressArr[0].split("\\;");
        String[] readAddress = addressArr[1].split("\\;");

        configs.add(createPoolDataSourceConfig(readAddress, PoolDataSourceConfig.PoolType.READ));
        configs.add(createPoolDataSourceConfig(writeAddress, PoolDataSourceConfig.PoolType.WRITE));

        return configs;
    }

    /**
     * @param entNumber
     * @param systemCode
     * @param poolType
     * @return
     */
    public static String tran2Key(int entNumber, String systemCode, PoolDataSourceConfig.PoolType poolType) {
        StringBuilder key = new StringBuilder();
        key.append(entNumber).append(Delimiters.UNDLER_LINE);
        key.append(systemCode).append(Delimiters.UNDLER_LINE);
        key.append(poolType.getIndex());
        return key.toString();
    }

    /**
     * @param tenantCode
     * @param productCode
     * @param dbType
     * @return
     */
    public static String tran2Key(Long tenantCode, Long productCode, TenantDBType dbType) {
        StringBuilder key = new StringBuilder();
        key.append(tenantCode).append(Delimiters.UNDLER_LINE);
        key.append(productCode).append(Delimiters.UNDLER_LINE);
        key.append(dbType.getDBType());
        return key.toString();
    }


    public static String tran2Key(Long tenantCode, Long productCode, TenantDBType dbType, String dbName) {
        StringBuilder key = new StringBuilder();
        key.append(tenantCode).append(Delimiters.UNDLER_LINE);
        key.append(productCode).append(Delimiters.UNDLER_LINE);
        key.append(dbType.getDBType()).append(Delimiters.UNDLER_LINE);
        key.append(dbName);
        return key.toString();
    }
}
