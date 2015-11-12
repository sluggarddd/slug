package com.slug.dao.impl;

import com.slug.dao.DataAccessor;
import com.slug.dao.DatabaseAdaptor;
import com.slug.orm.EntityAdaptor;
import com.slug.utils.MapUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/11/12
 */
public class DefaultDataAccessor implements DataAccessor {


    private final QueryRunner queryRunner;

    public DefaultDataAccessor() {
        DataSource dataSource = DatabaseAdaptor.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    @Override
    public <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {

        T result;
        try {

            Map<String, String> columnMap = EntityAdaptor.getColumnMap(entityClass);
            if (!MapUtils.isEmpty(columnMap)) {
                result = queryRunner.query(sql, new BeanHandler<T>(entityClass, new BasicRowProcessor(new BeanProcessor(columnMap))), params);
            } else {
                result = queryRunner.query(sql, new BeanHandler<T>(entityClass, params));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        printSQL(sql);
        return result;
    }

    @Override
    public <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public Object[] queryArray(String sql, Object... params) {
        return new Object[0];
    }

    @Override
    public List<Object[]> queryArrayList(String sql, Object... params) {
        return null;
    }

    @Override
    public Map<String, Object> queryMap(String sql, Object... params) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> T queryColumn(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> queryColumnList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return null;
    }

    @Override
    public long queryCount(String sql, Object... params) {
        return 0;
    }

    @Override
    public int update(String sql, Object... params) {
        return 0;
    }

    @Override
    public Serializable insertReturnPK(String sql, Object... params) {
        return null;
    }

    private static void printSQL(String sql) {
        System.out.println("[SLUG] SQL - {}" + sql);
    }
}
