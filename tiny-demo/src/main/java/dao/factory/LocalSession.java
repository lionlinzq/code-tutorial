package dao.factory;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author rongdi
 * @description 本地session，这里是每个用户进来都有一个自己的线程，各自使用自己线程内的session，然后在切面类里面在线程结束之前统一去提交
 * session的事务，此方法有一个漏洞就是已经默认了每个用户请求都必然在唯一一个事务中，但是可能有些情况下，一个用户请求可能包含多个事务，比如保存了a再保存b
 * 不管B是否保存成功都不会回滚,可以通过分开commit解决
 * @date 2015年11月5日
 */
public class LocalSession {

    private final static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    public static SqlSession getCurrentSqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSession session = local.get();
        if (session == null) {
            session = sqlSessionFactory.openSession();
            local.set(session);
        }
        return session;
    }

    public static void commit() {
        SqlSession session = local.get();
        if (session != null) {
            session.commit(true);
            session.close();
            session = null;
            local.remove();
        }
    }

    public static void commit(boolean force) {
        SqlSession session = local.get();
        if (session != null) {
            session.commit(force);
            session.close();
            session = null;
            local.remove();
        }
    }

    public static void rollback() {
        SqlSession session = local.get();
        if (session != null) {
            session.rollback(true);
            session.close();
            session = null;
            local.remove();
        }
    }

    public static void close() {
        SqlSession session = local.get();
        if (session != null) {
            session.close();
            session = null;
            local.remove();
        }
    }
}
