package com.slug.dao;

import com.slug.GlobalConfig;
import com.slug.orm.EntityAdaptor;
import com.slug.utils.CollectionUtils;
import com.slug.utils.MapUtils;
import com.slug.utils.PropsUtils;
import com.slug.utils.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * encapsulation sql operations
 *
 * @author zhw
 * @version 0.1  15/11/10
 */
public class SqlAdaptor {

    /**
     * sql props
     */
    private static final Properties sqlProps = PropsUtils.loadProps(GlobalConfig.SQL_PROPS);


    /**
     * get sql from sql props
     *
     * @param key
     * @return
     */
    public static String getSql(String key) {
        String sql;
        if (sqlProps.containsKey(key)) {
            sql = sqlProps.getProperty(key);
        } else {
            throw new RuntimeException("无法在 " + GlobalConfig.SQL_PROPS + "文件获取属性" + key);
        }
        return sql;
    }


    /**
     * generate select statement
     *
     * @param entityClass
     * @param condition
     * @param sort
     * @return
     */
    public static String generateSelectSql(Class<?> entityClass, String condition, String sort) {
        StringBuilder sql = new StringBuilder("select * from ").append(getTable(entityClass));
        sql.append(generateWhere(condition));
        sql.append(generateOrder(sort));
        return sql.toString();
    }


    /**
     * 生成insert语句
     *
     * @param entityClass
     * @param fieldNames
     * @return
     */
    public static String generateInsertSql(Class<?> entityClass, Collection<String> fieldNames) {

        StringBuilder sql = new StringBuilder("insert into ").append(getTable(entityClass));
        if (!CollectionUtils.isEmpty(fieldNames)) {
            int i = 0;
            StringBuilder columns = new StringBuilder(" ");
            StringBuilder values = new StringBuilder(" values ");
            for (String fieldName : fieldNames) {
                String columnName = EntityAdaptor.getColumnName(entityClass, fieldName);
                if (i == 0) {
                    columns.append("(").append(columnName);
                    values.append("(?");
                } else {
                    columns.append(", ").append(columnName);
                    values.append(", ?");
                }
                if (i == fieldNames.size() - 1) {
                    columns.append(")");
                    values.append(")");
                }
                i++;
            }
            sql.append(columns).append(values);
        }

        return sql.toString();

    }


    /**
     * 生成 delete 语句
     */
    public static String generateDeleteSql(Class<?> entityClass, String condition) {
        StringBuilder sql = new StringBuilder("delete from ").append(getTable(entityClass));
        sql.append(generateWhere(condition));
        return sql.toString();
    }


    /**
     * 生成 update 语句
     */
    public static String generateUpdateSql(Class<?> entityClass, Map<String, Object> fieldMap, String condition) {
        StringBuilder sql = new StringBuilder("update ").append(getTable(entityClass));
        if (!MapUtils.isEmpty(fieldMap)) {
            sql.append(" set ");
            int i = 0;
            for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
                String fieldName = fieldEntry.getKey();
                String columnName = EntityAdaptor.getColumnName(entityClass, fieldName);
                if (i == 0) {
                    sql.append(columnName).append(" = ?");
                } else {
                    sql.append(", ").append(columnName).append(" = ?");
                }
                i++;
            }
        }
        sql.append(generateWhere(condition));
        return sql.toString();
    }

    /**
     * 生成 select count(*) 语句
     */
    public static String generateSelectSqlForCount(Class<?> entityClass, String condition) {
        StringBuilder sql = new StringBuilder("select count(*) from ").append(getTable(entityClass));
        sql.append(generateWhere(condition));
        return sql.toString();
    }


    /**
     * 生成 select 分页语句（数据库类型为：mysql、oracle、mssql）
     */
    public static String generateSelectSqlForPager(int pageNumber, int pageSize, Class<?> entityClass, String condition, String sort) {
        StringBuilder sql = new StringBuilder();
        String table = getTable(entityClass);
        String where = generateWhere(condition);
        String order = generateOrder(sort);
        String dbType = DatabaseHelper.getDatabaseType();
        if (dbType.equalsIgnoreCase("mysql")) {
            int pageStart = (pageNumber - 1) * pageSize;
            appendSqlForMySql(sql, table, where, order, pageStart, pageSize);
        } else if (dbType.equalsIgnoreCase("oracle")) {
            int pageStart = (pageNumber - 1) * pageSize + 1;
            int pageEnd = pageStart + pageSize;
            appendSqlForOracle(sql, table, where, order, pageStart, pageEnd);
        } else if (dbType.equalsIgnoreCase("mssql")) {
            int pageStart = (pageNumber - 1) * pageSize;
            appendSqlForMsSql(sql, table, where, order, pageStart, pageSize);
        }
        return sql.toString();
    }


    private static String getTable(Class<?> entityClass) {
        return EntityAdaptor.getTableName(entityClass);
    }

    private static String generateWhere(String condition) {
        String where = "";
        if (!StringUtils.isEmpty(condition)) {
            where += " where " + condition;
        }
        return where;
    }


    private static String generateOrder(String sort) {
        String order = "";
        if (!StringUtils.isEmpty(sort)) {
            order += " order by " + sort;
        }
        return order;
    }


}
