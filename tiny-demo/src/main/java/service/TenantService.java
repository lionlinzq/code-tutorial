package service;

import com.xuanwu.apaas.core._enum.TenantDBType;

/**
 * @Description: 租户数据库
 * @Author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Date 2017年2月14日
 * @Version 1.0
 */
public interface TenantService {

    /**
     * 执行SQl脚本(默认连到租户写库)
     *
     * @param tenantCode
     * @param productCode
     * @param sql
     * @throws Exception
     */
    public boolean initDB(Long tenantCode, Long productCode, String sql) throws Exception;

    /**
     * 执行SQl脚本(默认连到租户写库)
     *一般用于执行function，语句中带分号实际要当作一条语句执行
     *
     * @param tenantCode
     * @param productCode
     * @param sql
     * @param sendFullScript 是否合量执行
     * @throws Exception
     */
    public boolean initDB(Long tenantCode, Long productCode, String sql, boolean sendFullScript) throws Exception;

    /**
     * 执行SQl脚本
     *
     * @param tenantCode
     * @param productCode
     * @param dataBaseType 数据库类型
     * @param sql
     * @return
     * @throws Exception
     */
    public boolean initDB(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql) throws Exception;

    /**
     * 一般用于执行function，语句中带分号实际要当作一条语句执行
     *
     * @param tenantCode
     * @param productCode
     * @param dataBaseType
     * @param sql
     * @param sendFullScript 是否合量执行
     * @return
     * @throws Exception
     */
    public boolean initDB(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql, boolean sendFullScript) throws Exception;

    /**
     * create by: yechh
     * description: 根据定义的资源库使用
     * create time:
     * @return
     */
    public boolean initDB( String sql,Long tenantCode, Long tenantdbCode) throws Exception;

}
