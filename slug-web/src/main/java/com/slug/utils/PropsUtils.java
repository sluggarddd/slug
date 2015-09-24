package com.slug.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
                //todo ¼ÇÂ¼logo
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
            //todo ¼ÇÂ¼logo
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                //todo ¼ÇÂ¼logo
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


}
