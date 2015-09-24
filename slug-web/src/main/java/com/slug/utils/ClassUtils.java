package com.slug.utils;

import java.net.URL;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public class ClassUtils {


    /**
     * obtain the class loader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * obtain the class path
     *
     * @return
     */
    public static String getClassPath() {

        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }


    /**
     * 加载类(自动初始化)
     *
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }


    /**
     * 加载类
     *
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {

        Class<?> cls;

        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cls;

    }


    public static boolean isInt(Class<?> type) {

        return type.equals(int.class) || type.equals(Integer.class);

    }

    public static boolean isLong(Class<?> type) {

        return type.equals(long.class) || type.equals(Long.class);

    }

    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }

}
