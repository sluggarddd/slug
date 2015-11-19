package com.slug.orm;

import com.slug.GlobalConfig;
import com.slug.dao.DatabaseHandler;
import com.slug.dao.SqlAdaptor;
import com.slug.utils.ArrayUtils;
import com.slug.utils.MapUtils;
import com.slug.utils.ObjectUtils;
import com.sun.deploy.util.ArrayUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * provide operation between database and class
 *
 * @author zhw
 * @version 0.1  15/11/10
 */
public class DataSet {


    /**
     * 查询单条数据，并转为相应类型的实体
     */
    public static <T> T select(Class<T> entityClass, String condition, Object... params) {
        String sql = SqlAdaptor.generateSelectSql(entityClass, condition, "");
        return DatabaseHandler.queryEntity(entityClass, sql, params);
    }

    /**
     * 查询多条数据，并转为相应类型的实体列表
     */
    public static <T> List<T> selectList(Class<T> entityClass) {
        return selectListWithConditionAndSort(entityClass, "", "");
    }

    /**
     * 查询多条数据，并转为相应类型的实体列表（带有查询条件与查询参数）
     */
    public static <T> List<T> selectListWithCondition(Class<T> entityClass, String condition, Object... params) {
        return selectListWithConditionAndSort(entityClass, condition, "", params);
    }

    /**
     * 查询多条数据，并转为相应类型的实体列表（带有排序方式）
     */
    public static <T> List<T> selectListWithSort(Class<T> entityClass, String sort) {
        return selectListWithConditionAndSort(entityClass, "", sort);
    }

    /**
     * 查询多条数据，并转为相应类型的实体列表（带有查询条件、排序方式与查询参数）
     */
    public static <T> List<T> selectListWithConditionAndSort(Class<T> entityClass, String condition, String sort, Object... params) {
        String sql = SqlAdaptor.generateSelectSql(entityClass, condition, sort);
        return DatabaseHandler.queryEntityList(entityClass, sql, params);
    }

    /**
     * 查询数据条数
     */
    public static long selectCount(Class<?> entityClass, String condition, Object... params) {
        String sql = SqlAdaptor.generateSelectSqlForCount(entityClass, condition);
        return DatabaseHandler.queryCount(sql, params);
    }

    /**
     * 查询多条数据，并转为列表（分页方式）
     */
    public static <T> List<T> selectListForPager(int pageNumber, int pageSize, Class<T> entityClass, String condition, String sort, Object... params) {
        String sql = SqlAdaptor.generateSelectSqlForPager(pageNumber, pageSize, entityClass, condition, sort);
        return DatabaseHandler.queryEntityList(entityClass, sql, params);
    }

    /**
     * 查询多条数据，并转为映射
     */
    public static <T> Map<Long, T> selectMap(Class<T> entityClass) {
        return selectMapWithPK(entityClass, GlobalConfig.PK_NAME, "", "");
    }

    /**
     * 查询多条数据，并转为映射（带有查询条件与查询参数）
     */
    public static <T> Map<Long, T> selectMapWithCondition(Class<T> entityClass, String condition, Object... params) {
        return selectMapWithPK(entityClass, GlobalConfig.PK_NAME, condition, "", params);
    }

    /**
     * 查询多条数据，并转为映射（带有排序方式与查询参数）
     *
     * @since 2.3.3
     */
    public static <T> Map<Long, T> selectMapWithSort(Class<T> entityClass, String sort) {
        return selectMapWithPK(entityClass, GlobalConfig.PK_NAME, "", sort);
    }

    /**
     * 查询多条数据，并转为映射（带有查询条件、排序方式与查询参数）
     */
    public static <T> Map<Long, T> selectMapWithConditionAndSort(Class<T> entityClass, String condition, String sort, Object... params) {
        return selectMapWithPK(entityClass, GlobalConfig.PK_NAME, condition, sort, params);
    }

    /**
     * 查询多条数据，并转为映射（带有主键名）
     */
    @SuppressWarnings("unchecked")
    public static <PK, T> Map<PK, T> selectMapWithPK(Class<T> entityClass, String pkName, String condition, String sort, Object... params) {
        Map<PK, T> map = new LinkedHashMap<PK, T>();
        List<T> list = selectListWithConditionAndSort(entityClass, condition, sort, params);
        for (T obj : list) {
            PK pk = (PK) ObjectUtils.getFieldValue(obj, pkName);
            map.put(pk, obj);
        }
        return map;
    }

    /**
     * 根据列名查询单条数据，并转为相应类型的实体
     */
    public static <T> T selectColumn(Class<?> entityClass, String columnName, String condition, Object... params) {
        String sql = SqlAdaptor.generateSelectSql(entityClass, condition, "");
        sql = sql.replace("*", columnName);
        return DatabaseHandler.queryColumn(sql, params);
    }

    /**
     * 根据列名查询多条数据，并转为相应类型的实体列表
     */
    public static <T> List<T> selectColumnList(Class<?> entityClass, String columnName, String condition, String sort, Object... params) {
        String sql = SqlAdaptor.generateSelectSql(entityClass, condition, sort);
        sql = sql.replace("*", columnName);
        return DatabaseHandler.queryColumnList(sql, params);
    }

    /**
     * 插入一条数据
     */
    public static boolean insert(Class<?> entityClass, Map<String, Object> fieldMap) {
        if (MapUtils.isEmpty(fieldMap)) {
            return true;
        }
        String sql = SqlAdaptor.generateInsertSql(entityClass, fieldMap.keySet());
        int rows = DatabaseHandler.update(sql, fieldMap.values().toArray());
        return rows > 0;
    }

    /**
     * 插入一个实体
     */
    public static boolean insert(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        Class<?> entityClass = entity.getClass();
        Map<String, Object> fieldMap = ObjectUtils.getFieldMap(entity);
        return insert(entityClass, fieldMap);
    }

    /**
     * 更新相关数据
     */
    public static boolean update(Class<?> entityClass, Map<String, Object> fieldMap, String condition, Object... params) {
        if (MapUtils.isEmpty(fieldMap)) {
            return true;
        }
        String sql = SqlAdaptor.generateUpdateSql(entityClass, fieldMap, condition);
        int rows = DatabaseHandler.update(sql, ArrayUtils.concat(fieldMap.values().toArray(), params));
        return rows > 0;
    }

    /**
     * 更新一个实体
     */
    public static boolean update(Object entity) {
        return update(entity, GlobalConfig.PK_NAME);
    }

    /**
     * 更新一个实体（带有主键名）
     */
    public static boolean update(Object entityObject, String pkName) {
        if (entityObject == null) {
            throw new IllegalArgumentException();
        }
        Class<?> entityClass = entityObject.getClass();
        Map<String, Object> fieldMap = ObjectUtils.getFieldMap(entityObject);
        String condition = pkName + " = ?";
        Object[] params = {ObjectUtils.getFieldValue(entityObject, pkName)};
        return update(entityClass, fieldMap, condition, params);
    }

    /**
     * 删除相关数据
     */
    public static boolean delete(Class<?> entityClass, String condition, Object... params) {
        String sql = SqlAdaptor.generateDeleteSql(entityClass, condition);
        int rows = DatabaseHandler.update(sql, params);
        return rows > 0;
    }

    /**
     * 删除一个实体
     */
    public static boolean delete(Object entityObject) {
        return delete(entityObject, GlobalConfig.PK_NAME);
    }

    /**
     * 删除一个实体（可指定主键名）
     */
    public static boolean delete(Object entityObject, String pkName) {
        if (entityObject == null) {
            throw new IllegalArgumentException();
        }
        Class<?> entityClass = entityObject.getClass();
        String condition = pkName + " = ?";
        Object[] params = {ObjectUtils.getFieldValue(entityObject, pkName)};
        return delete(entityClass, condition, params);
    }


}
