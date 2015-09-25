package com.slug.utils;

import java.io.InputStream;
import java.util.*;

/**
 * properties file operate utils
 *
 * @author zhw
 * @version 0.1  15/9/24
 */
public class PropsUtils {


    /**
     * load properties
     *
     * @param path
     * @return
     */
    public static Properties loadProps(String path) {

        Properties properties = new Properties();

        InputStream is = null;

        try {

            if (StringUtils.isEmpty(path)) {
                //todo  add log
                throw new IllegalAccessException();
            }

            String suffix = ".properties";
            if (path.lastIndexOf(suffix) == -1) {
                path += suffix;
            }

            is = ClassUtils.getClassLoader().getResourceAsStream(path);

            if (is != null) {
                properties.load(is);
            }
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                //todo add log
            }
        }


        return properties;
    }


    public static Map<String, String> loadPropsToMap(String path) {

        Map<String, String> map = new HashMap<String, String>();
        Properties properties = loadProps(path);
        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }
        return map;


    }


    public static String getString(Properties properties, String key) {
        String value = "";
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }


    public static Map<String, Object> getPropsMap(Properties properties, String prefix) {

        Map<String, Object> propsMap = new LinkedHashMap<String, Object>();

        Set<String> keySet = properties.stringPropertyNames();

        if (CollectionUtils.isEmpty(keySet)) {
            for (String key : keySet) {
                if (key.startsWith(prefix)) {
                    String value = properties.getProperty(key);
                    propsMap.put(key, value);
                }
            }
        }

        return propsMap;
    }


}
