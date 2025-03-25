package dao.impl;


import _enum.TenantDBType;
import dao.Operate;
import dao.factory.LocalSession;
import dao.factory.TenantSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import utils.DataSourceUtils;

/**
 * @author rongdi
 * @description mybatis操作实现类
 * @date 2015年11月4日
 */
@Configuration
public class TenantOperate extends Operate {
    private static final Logger logger = LoggerFactory.getLogger(TenantOperate.class);
    private TenantDBType dbType;
    private SqlSessionFactory sqlSessionFactory;

    static TenantSessionFactory tenantSessionFactory;

    public TenantOperate() {
    }

    private String key;

    public TenantOperate(Long tenantCode, Long productCode, TenantDBType dbType) {
        key = DataSourceUtils.tran2Key(tenantCode, productCode, dbType);
        this.dbType = dbType;
        sqlSessionFactory = tenantSessionFactory.getTenantDruidSessionFactory(tenantCode, productCode, dbType);
    }

    public TenantOperate(Long tenantCode, Long productCode, TenantDBType dbType, String dbName) {
        key = DataSourceUtils.tran2Key(tenantCode, productCode, dbType, dbName);
        this.dbType = dbType;
        sqlSessionFactory = tenantSessionFactory.getTenantDruidSessionFactory(tenantCode, productCode, dbType, dbName);
    }

    @Override
    public SqlSession getCurrentSession() {
        logger.debug("getCurrentSession==== dbtype:{}, key:{}", this.dbType.getDBType(), key);
        tenantSessionFactory.setTenantSouceCurrentKey(key);
        return LocalSession.getCurrentSqlSession(sqlSessionFactory);
    }

    @Override
    public SqlSession openSession() {
        logger.debug("openSession==== dbtype:{}, key:{}", this.dbType.getDBType(), key);
        tenantSessionFactory.setTenantSouceCurrentKey(key);
        return sqlSessionFactory.openSession();
    }

    @Autowired
    public void setXwSessionFactory(TenantSessionFactory tenantSessionFactory) {
        TenantOperate.tenantSessionFactory = tenantSessionFactory;
    }

}
