package com.slug.utils;

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

}
