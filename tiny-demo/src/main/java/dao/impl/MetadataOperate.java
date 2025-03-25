package dao.impl;

import com.xuanwu.apaas.core.dao.Operate;
import com.xuanwu.apaas.core.dao.factory.LocalSession;
import com.xuanwu.apaas.core.dao.factory.MetadataSessionFactory;
import com.xuanwu.apaas.core.utils.PoolDataSourceConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rongdi
 * @description mybatis操作实现类
 * @date 2015年11月4日
 */
@Component
public class MetadataOperate extends Operate {

    private SqlSessionFactory sqlSessionFactory;

    static MetadataSessionFactory metadataSessionFactory;

    public MetadataOperate() {
    }

    public MetadataOperate(PoolDataSourceConfig.PoolType type) {
        sqlSessionFactory = metadataSessionFactory.getSinggleSessionFactory(type);
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
    public void setXwSessionFactory(MetadataSessionFactory metadataSessionFactory) {
        MetadataOperate.metadataSessionFactory = metadataSessionFactory;
    }

}
