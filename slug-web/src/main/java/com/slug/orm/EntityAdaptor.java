package com.slug.orm;

import com.slug.core.ClassHandler;
import com.slug.orm.annotation.Column;
import com.slug.orm.annotation.Entity;
import com.slug.orm.annotation.Table;
import com.slug.utils.ArrayUtils;
import com.slug.utils.MapUtils;
import com.slug.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/11/9
 */
public class EntityAdaptor {


    /**
     * class---table name
     */
    private static final Map<Class<?>, String> entityTableMap = new HashMap<Class<?>, String>();


    /**
     * class -- (field name -- column)
     */
    private static final Map<Class<?>, Map<String, String>> entityFieldsMap = new HashMap<Class<?>, Map<String, String>>();


    static {
        List<Class<?>> entityClassList = ClassHandler.getClassListByAnnotation(Entity.class);
        for (Class<?> entityClass : entityClassList) {
            initEntityTableMap(entityClass);
        }
    }


    private static void initEntityTableMap(Class<?> entityClass) {

        //if the class covered by table annotation
        String tableName;
        if (entityClass.isAnnotationPresent(Table.class)) {

            //if it has been assigned table name
            tableName = entityClass.getAnnotation(Table.class).value();
        } else {

            //if not exist ,it will transfer to the underline style
            tableName = StringUtils.camelHumpToUnderline(entityClass.getSimpleName());
        }

        entityTableMap.put(entityClass, tableName);

    }

    private static void initEntityFieldMap(Class<?> entityClass) {

        //get the fields in entity
        Field[] fields = entityClass.getDeclaredFields();

        if (!ArrayUtils.isEmpty(fields)) {

            Map<String, String> fieldMap = new HashMap<String, String>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String columnName;

                //if the field covered annotation Column
                if (field.isAnnotationPresent(Column.class)) {
                    columnName = field.getAnnotation(Column.class).value();
                } else {
                    columnName = StringUtils.camelHumpToUnderline(fieldName);
                }

                fieldMap.put(fieldName, columnName);

                entityFieldsMap.put(entityClass, fieldMap);

            }
        }

    }


    public static String getTableName(Class<?> entityClass) {
        return entityTableMap.get(entityClass);
    }


    public static Map<String, String> getFieldMap(Class<?> entityClass) {
        return entityFieldsMap.get(entityClass);
    }

    public static Map<String, String> getColumnMap(Class<?> entityClass) {
        return MapUtils.invert(getFieldMap(entityClass));
    }

    public static String getColumnName(Class<?> entityClass, String fieldName) {
        String columnName = getFieldMap(entityClass).get(fieldName);
        return StringUtils.isEmpty(columnName) ? fieldName : columnName;
    }
}
