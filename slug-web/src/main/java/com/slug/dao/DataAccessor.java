package com.slug.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/11/12
 */
public interface DataAccessor {

    /**
     * query for entity and return single record
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> T queryEntity(Class<T> entityClass, String sql, Object... params);

    /**
     * query for entity collections
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params);


    /**
     * query for entity collections,return single record
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params);


    /**
     * query for entity collections,return single record
     *
     * @param sql
     * @param params
     * @return
     */
    Object[] queryArray(String sql, Object... params);


    /**
     * query for entity collections,return many records
     *
     * @param sql
     * @param params
     * @return
     */
    List<Object[]> queryArrayList(String sql, Object... params);


    /**
     * query for entity collections,return single record
     *
     * @param sql
     * @param params
     * @return
     */
    Map<String, Object> queryMap(String sql, Object... params);


    /**
     * query for entity collections,return many record
     *
     * @param sql
     * @param params
     * @return
     */
    List<Map<String, Object>> queryMapList(String sql, Object... params);


    /**
     * query for entity collections,return single record
     *
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> T queryColumn(String sql, Object... params);

    /**
     * query for entity collections,return many record
     *
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> queryColumnList(String sql, Object... params);

    /**
     * @param column
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params);


    /**
     * 查询记录条数，返回总记录数
     */
    long queryCount(String sql, Object... params);

    /**
     * 执行更新操作（包括：update、insert、delete），返回所更新的记录数
     */
    int update(String sql, Object... params);

    /**
     * 插入一条记录，返回插入后的主键
     */
    Serializable insertReturnPK(String sql, Object... params);
}
