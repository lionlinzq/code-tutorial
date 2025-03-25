package utils;


import _enum.TenantDBType;
import service.IDataSourceService;
import service.impl.IDataSourceServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author liangzhi
 * @description 对外提供工具类来路由数据库信息
 */
public class DataSourceRouterUtil {
    private DataSourceRouterUtil() {
    }

    private static IDataSourceService dataSourceService;

    static {
        //dataSourceService = new DataSourceServiceImpl();
        dataSourceService = new IDataSourceServiceImpl();
    }

    /**
     * 获取租户数据库的数据源对象
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 数据库类型
     * @param productCode  行业编码
     * @return 租户数据库的数据源对象
     */
    public static DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception {
        return dataSourceService.getDataSource(tenantCode, tenantDBType, productCode);
    }

    public static DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) throws Exception {
        return dataSourceService.getDataSource(tenantCode, tenantDBType, productCode, tenantDbName);
    }

    public static DataSource getDataSource(Long tenantCode, long tenantDbCode) throws Exception {
        return dataSourceService.getDataSource(tenantCode, tenantDbCode);
    }


    /**
     * 重新加载指定租户数据库的信息 --适用于系统启动后修改了租户数据库信息后重新加载
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 数据库类型
     * @param productCode  行业编码
     */
    public static void reloadSingleDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception {
        dataSourceService.reloadSingleDataSource(tenantCode, tenantDBType, productCode);
    }

    /**
     * 获取租户数据库连接
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 租户类型
     * @param productCode  产品编码
     * @return 数据库连接对象
     * @throws Exception
     */
    public static Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode, Long tenantDbCode) throws SQLException {
        return dataSourceService.getConnection(tenantCode, tenantDBType, productCode, tenantDbCode);
    }

    public static Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode, Long tenantDbCode, String tenantDbName) throws SQLException {
        return dataSourceService.getConnection(tenantCode, tenantDBType, productCode, tenantDbCode, tenantDbName);
    }


    public static List<Map<String, String>> listAll() {
        return dataSourceService.listAll();
    }

    public static void clearAll() {
        dataSourceService.clearAll();
    }

    public static void clear(Long tenantCode, TenantDBType tenantDBType, Long productCode) {
        dataSourceService.clear(tenantCode, tenantDBType, productCode);
    }

}

