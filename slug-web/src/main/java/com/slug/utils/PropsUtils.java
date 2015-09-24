package com.slug.utils;

import java.io.InputStream;
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
    public static Properties loadProps(String path) throws Exception {

        Properties properties = new Properties();

        InputStream is = null;

        if (StringUtils.isEmpty(path)) {
            throw new IllegalAccessException();
        }

        String suffix = ".properties";
        if (path.lastIndexOf(suffix) == -1) {
            path += suffix;
        }

        return null;
    }
}
