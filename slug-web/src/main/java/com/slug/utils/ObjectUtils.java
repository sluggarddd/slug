package com.slug.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class ObjectUtils {


    public static <T> T newInstance(String className) {

        T instance;

        try {
            Class<?> classLoad = ClassUtils.loadClass(className);
            instance = (T) classLoad.newInstance();
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

        return instance;


    }


    /**
     * 获取成员变量
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object propertyValue = null;
        try {
            if (PropertyUtils.isReadable(obj, fieldName)) {
                propertyValue = PropertyUtils.getProperty(obj, fieldName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    /**
     * 获取对象的字段映射（字段名 => 字段值），忽略 static 字段
     */
    public static Map<String, Object> getFieldMap(Object obj) {
        return getFieldMap(obj, true);
    }


    /**
     * 获取对象的字段映射（字段名 => 字段值）
     */
    public static Map<String, Object> getFieldMap(Object obj, boolean isStaticIgnored) {
        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isStaticIgnored && Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String fieldName = field.getName();
            Object fieldValue = ObjectUtils.getFieldValue(obj, fieldName);
            fieldMap.put(fieldName, fieldValue);
        }
        return fieldMap;
    }
}
