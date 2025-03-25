package service;


import _enum.TenantDBType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author liangzhi
 * @description 数据源路由服务
 */
public interface IDataSourceService {
    /**
     * 获取租户的数据源对象:根据租户编码、数据库类型、行业编码获取连接数据库对象
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 数据库分类
     * @param productCode  行业编码
     * @return 数据库实例
     */
    DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception;

    DataSource getDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) throws Exception;

    DataSource getDataSource(Long tenantCode, Long tenantDbCode) throws  Exception;

    /**
     * 重新加载指定租户的数据源信息
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 数据库分类
     * @param productCode  行业编码
     */
    void reloadSingleDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) throws Exception;


    /**
     * 获取租户数据库连接
     *
     * @param tenantCode   租户编码
     * @param tenantDBType 租户类型
     * @param productCode  产品编码
     * @return 数据库连接对象
     * @throws Exception
     */
    Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode, Long tenantDbCode) throws SQLException;


    /**
     * 查询所有缓存中的租户数据源信息
     *
     * @return
     * @throws Exception
     */
    List<Map<String, String>> listAll();


    void reloadAll();

    void clearAll();

    void clear(Long tenantCode, TenantDBType tenantDBType, Long productCode);


    Connection getConnection(Long tenantCode, TenantDBType tenantDBType, Long productCode, Long tenantDbCode, String tenantDbName) throws SQLException;
}

