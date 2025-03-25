package dao;

import com.xuanwu.apaas.core.dao.factory.LocalSession;
import com.xuanwu.apaas.core.exception.DataAccessException;
import com.xuanwu.apaas.core.utils.ListUtil;
import com.xuanwu.apaas.core.utils.PageInfo;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author rongdi
 * @description 抽象的数据库操作接口
 * @date 2015年11月5日
 */
public abstract class Operate {

    public abstract SqlSession getCurrentSession();

    public abstract SqlSession openSession();

    public void clearSqlSessionFactory() {
    }

    public <T> boolean save(T entity) {
        Class<?> clazz = entity.getClass();
        SqlSession session = null;
        int i = 0;
        try {
            session = openSession();
            i = session.insert(clazz.getName() + ".insert", entity);
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw new DataAccessException("save " + clazz.getSimpleName() + " error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
        return i > 0;
    }

    public <T> boolean save(T entity, boolean isAutoCommit) {
        Class<?> clazz = entity.getClass();
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        int i = 0;
        try {
            i = session.insert(clazz.getName() + ".insert", entity);
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback();
            } else {
                rollback(true);
            }
            throw new DataAccessException("save " + clazz.getSimpleName() + " error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.close();
            }
        }
        return i > 0;
    }

    public <T> boolean update(T entity) {
        Class<?> clazz = entity.getClass();
        SqlSession session = null;
        int i = 0;
        try {
            session = openSession();
            i = session.update(clazz.getName() + ".update", entity);
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
        return i > 0;
    }

    public <T> boolean update(T entity, boolean isAutoCommit) {
        Class<?> clazz = entity.getClass();
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        int i = 0;
        try {
            i = session.update(clazz.getName() + ".update", entity);
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback();
            } else {
                rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
        return i > 0;
    }

    public <T> boolean isExist(String sqlId, Class<?> clazz, T t) {
        SqlSession session = null;
        try {
            session = openSession();
            Long n = (Long) session.selectOne(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            throw new DataAccessException("isExist " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T> boolean isExist(String sqlId, Class<?> clazz, T t, boolean isAutoCommit) {
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        try {
            Long n = (Long) session.selectOne(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            if (!isAutoCommit) {
                rollback(true);
            }
            throw new DataAccessException("isExist " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.close();
            }
        }
    }

    public <T> T singleResult(String sqlId, Class<?> clazz, Map<String, Object> map) {

        T t = null;
        SqlSession session = null;
        try {
            session = openSession();
            t = session.selectOne(clazz.getName() + "." + sqlId, map);
        } catch (Exception e) {
            rollback(true);
            throw new DataAccessException("singleResult " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return t;
    }

    public <T> T singleResult(String sqlId, Class<?> clazz, Map<String, Object> map, boolean isAutoCommit) {
        T t = null;
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        try {
            t = session.selectOne(clazz.getName() + "." + sqlId, map);
        } catch (Exception e) {
            if (!isAutoCommit) {
                rollback(true);
            }
            throw new DataAccessException("singleResult " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.close();
            }

        }
        return t;
    }

    public <T> List<T> list(String sqlId, Class<?> clazz, Map<String, Object> map) {
        List<T> ts = null;
        SqlSession session = null;
        try {
            session = openSession();
            ts = session.selectList(clazz.getName() + "." + sqlId, map);
        } catch (Exception e) {
            throw new DataAccessException("list " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ts;
    }


    public <T> void cursorList(String sqlId, Class<?> clazz, Map<String, Object> map, Consumer<T> handler) {
        try (SqlSession session = openSession(); Cursor<T> cursor = session.selectCursor(clazz.getName() + "." + sqlId, map)) {
            for (T item : cursor) {
                handler.accept(item);
            }
        } catch (Exception e) {
            throw new DataAccessException("cursorList " + clazz.getSimpleName() + " data error", e);
        }
    }


    public <T> List<T> list(String sqlId, Class<?> clazz, Map<String, Object> map, boolean isAutoCommit) {
        List<T> ts = null;
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        try {
            ts = session.selectList(clazz.getName() + "." + sqlId, map);
        } catch (Exception e) {
            if (!isAutoCommit) {
                rollback(true);
            }
            throw new DataAccessException("list " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.close();
            }
        }
        return ts;
    }

    public <T> PageInfo<T> pageList(String sqlId, Class<?> clazz, Map<String, Object> map) {
        int pageno = MapUtils.getInteger(map, "page", 1);
        int pagesize = MapUtils.getInteger(map, "rows", 9999);
        map.put("offset", (pageno - 1) * pagesize);
        map.put("pagesize", pagesize);
        Long count = 0L;
        List<T> data = new ArrayList<T>();
        SqlSession session = null;
        try {
            session = openSession();
            count = session.selectOne(clazz.getName() + "." + sqlId + "Count", map);
            if (count > 0) {
                data = session.selectList(clazz.getName() + "." + sqlId, map);
            }
        } catch (Exception e) {
            throw new DataAccessException("pageList " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return new PageInfo<T>(pageno, count, pagesize, data);
    }

    public <T> PageInfo<T> pageList(String sqlId, Class<?> clazz, Map<String, Object> map, boolean isAutoCommit) {
        int pageno = MapUtils.getInteger(map, "page", 1);
        int pagesize = MapUtils.getInteger(map, "rows", 9999);
        map.put("offset", (pageno - 1) * pagesize);
        map.put("pagesize", pagesize);
        Long count = 0L;
        List<T> data = new ArrayList<T>();
        SqlSession session = null;
        if (isAutoCommit) {
            session = openSession();
        } else {
            session = getCurrentSession();
        }
        try {
            count = session.selectOne(clazz.getName() + "." + sqlId + "Count", map);
            if (count > 0) {
                data = session.selectList(clazz.getName() + "." + sqlId, map);
            }
        } catch (Exception e) {
            if (!isAutoCommit) {
                rollback(true);
            }
            throw new DataAccessException("pageList " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.close();
            }
        }
        return new PageInfo<T>(pageno, count, pagesize, data);
    }

    public <T> boolean insert(String sqlId, Class<?> clazz, T t) {
        SqlSession session = null;
        try {
            session = openSession();
            int n = session.insert(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("insert " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean insert(String sqlId, Class<?> clazz, T t, boolean isAutoCommit) {
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            int n = session.insert(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("insert " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }


    public <T> boolean insertBatch(String sqlId, Class<?> clazz, List<T> ts) {
        if (ListUtil.isBlank(ts)) {
            return false;
        }
        SqlSession session = null;
        try {
            session = openSession();
            List<List<T>> tsList = ListUtil.splitList(ts, 1000);
            for (List<T> list : tsList) {
                session.insert(clazz.getName() + "." + sqlId, list);
            }
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("insert " + clazz.getSimpleName() + " datas error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean insertBatch(String sqlId, Class<?> clazz, List<T> ts, boolean isAutoCommit) {
        if (ListUtil.isBlank(ts)) {
            return false;
        }
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            List<List<T>> tsList = ListUtil.splitList(ts, 1000);
            for (List<T> list : tsList) {
                session.insert(clazz.getName() + "." + sqlId, list);
            }
            return true;
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("insert " + clazz.getSimpleName() + " datas error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }


    public <T> boolean update(String sqlId, Class<?> clazz, T t, boolean isAutoCommit) {
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            int n = session.update(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> int updateAndGetRows(String sqlId, Class<?> clazz, T t, boolean isAutoCommit) {
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            return session.update(clazz.getName() + "." + sqlId, t);
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean update(String sqlId, Class<?> clazz, T t) {
        SqlSession session = null;
        try {
            session = openSession();
            int n = session.update(clazz.getName() + "." + sqlId, t);
            return n > 0;
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean update(String sqlId, Class<?> clazz, List<T> ts) {
        if (ListUtil.isBlank(ts)) {
            return false;
        }
        SqlSession session = null;
        try {
            session = openSession();
            for (T t : ts) {
                session.update(clazz.getName() + "." + sqlId, t);
            }
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " datas error", e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean update(String sqlId, Class<?> clazz, List<T> ts, boolean isAutoCommit) {
        if (ListUtil.isBlank(ts)) {
            return false;
        }
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            for (T t : ts) {
                session.update(clazz.getName() + "." + sqlId, t);
            }
            return true;
        } catch (Exception e) {
            if (isAutoCommit && session != null) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("update " + clazz.getSimpleName() + " datas error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean delete(String sqlId, Class<?> clazz, Map<String, Object> map) {
        SqlSession session = null;
        try {
            session = openSession();
            session.delete(clazz.getName() + "." + sqlId, map);
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.rollback(true);
            }
            throw new DataAccessException("delete " + clazz.getSimpleName() + " data error " + e.getMessage(), e);
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> boolean delete(String sqlId, Class<?> clazz, Map<String, Object> map, boolean isAutoCommit) {
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            session.delete(clazz.getName() + "." + sqlId, map);
            return true;
        } catch (Exception e) {
            if (isAutoCommit) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("delete " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public <T> int deleteAndGetRows(String sqlId, Class<?> clazz, Map<String, Object> map, boolean isAutoCommit) {
        SqlSession session = null;
        try {
            if (isAutoCommit) {
                session = openSession();
            } else {
                session = getCurrentSession();
            }
            return session.delete(clazz.getName() + "." + sqlId, map);
        } catch (Exception e) {
            if (isAutoCommit) {
                session.rollback(true);
            } else {
                rollback(true);
            }
            throw new DataAccessException("delete " + clazz.getSimpleName() + " data error", e);
        } finally {
            if (isAutoCommit && session != null) {
                session.commit();
                session.close();
            }
        }
    }

    public void commit(boolean force) {
        clearSqlSessionFactory();
        LocalSession.commit(force);
    }

    public void close() {
        LocalSession.close();
    }

    public void commit() {
        commit(true);
    }

    public void rollback(boolean force) {
        clearSqlSessionFactory();
        LocalSession.rollback();
    }

}
