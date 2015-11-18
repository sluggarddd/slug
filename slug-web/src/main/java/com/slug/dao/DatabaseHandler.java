package com.slug.dao;

import com.slug.ApplicationContext;
import com.slug.core.ConfigHandler;
import com.slug.datasource.DataSourceFactory;
import com.slug.utils.ClassUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * encapsulation database operation
 *
 * @author zhw
 * @version 0.1  15/11/12
 */
public class DatabaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);


    /**
     * define a local thread，every thread owns its connection
     */
    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    /**
     * get datasource factory
     */
    private static final DataSourceFactory dataSourceFactory = ApplicationContext.getDataSourceFactory();

    /**
     * obtain data accessor
     */
    private static final DataAccessor dataAccessor = ApplicationContext.getDataAccessor();

    /**
     * database type
     */
    private static final String databaseType = ConfigHandler.getString("com.slug.jdbc.type");


    /**
     * obtain database type
     *
     * @return
     */
    public static String getDatabaseType() {
        return databaseType;
    }

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }


    /**
     * obtain database connection
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = connContainer.get();
            if (connection == null) {
                connection = getDataSource().getConnection();

                if (connection != null) {
                    connContainer.set(connection);
                }
            }
        } catch (SQLException e) {
            logger.error("获取数据库链接出错", e);
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static void beginTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.error("开启事务出错", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.set(connection);
            }
        }
    }


    /**
     * 提交事务
     */
    public static void commitTransaction() {

        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                logger.error("提交事务出错", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }


    public static void rollbackTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                logger.error("rollback error", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }


    public static void initSQL(String sqlPath) {
        try {
            File sqlFile = new File(ClassUtils.getClassPath() + sqlPath);
            List<String> sqlList = FileUtils.readLines(sqlFile);
            for (String sql : sqlList) {
                update(sql);
            }
        } catch (Exception e) {
            logger.error("init sql footprint error", e);
            throw new RuntimeException(e);
        }
    }


    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntity(entityClass, sql, params);
    }


    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityList(entityClass, sql, params);
    }

    public static <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityMap(entityClass, sql, params);
    }


    public static Object[] queryArray(String sql, Object... params) {
        return dataAccessor.queryArray(sql, params);
    }

    public static List<Object[]> queryArrayList(String sql, Object... params) {
        return dataAccessor.queryArrayList(sql, params);
    }

    public static Map<String, Object> queryMap(String sql, Object... params) {
        return dataAccessor.queryMap(sql, params);
    }

    public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return dataAccessor.queryMapList(sql, params);
    }

    public static <T> T queryColumn(String sql, Object... params) {
        return dataAccessor.queryColumn(sql, params);
    }

    public static <T> List<T> queryColumnList(String sql, Object... params) {
        return dataAccessor.queryColumnList(sql, params);
    }

    public static <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return dataAccessor.queryColumnMap(column, sql, params);
    }

    public static long queryCount(String sql, Object... params) {
        return dataAccessor.queryCount(sql, params);
    }

    public static int update(String sql, Object... params) {
        return dataAccessor.update(sql, params);
    }

    public static Serializable insertReturnPK(String sql, Object... params) {
        return dataAccessor.insertReturnPK(sql, params);
    }
}
