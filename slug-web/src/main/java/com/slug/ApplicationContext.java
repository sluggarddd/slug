package com.slug;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public class ApplicationContext {


    private static final Map<String, Object> instances = new ConcurrentHashMap<String, Object>();

    public static <T> T getInstance(String key, Class<T> defaultImplClass) {

        if (instances.containsKey(key)) {
            return (T) instances.get(key);
        }


        return null;

    }


}
