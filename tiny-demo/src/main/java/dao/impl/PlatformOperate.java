package dao.impl;


import dao.Operate;
import dao.factory.LocalSession;
import dao.factory.PlatformSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import utils.PoolDataSourceConfig;

/**
 * @author rongdi
 * @description mybatis操作实现类
 * @date 2015年11月4日
 */
@Configuration
public class PlatformOperate extends Operate {

    private SqlSessionFactory sqlSessionFactory;

    static PlatformSessionFactory platformSessionFactory;

    public PlatformOperate() {

    }

    public PlatformOperate(PoolDataSourceConfig.PoolType type) {
        sqlSessionFactory = platformSessionFactory.getSinggleSessionFactory(type);
    }

    @Override
    public SqlSession getCurrentSession() {
        return LocalSession.getCurrentSqlSession(sqlSessionFactory);
    }

    @Override
    public SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    @Autowired
    public void setXwSessionFactory(PlatformSessionFactory platformSessionFactory) {
        PlatformOperate.platformSessionFactory = platformSessionFactory;
    }

}
