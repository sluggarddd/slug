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
        return null;
    }
}
