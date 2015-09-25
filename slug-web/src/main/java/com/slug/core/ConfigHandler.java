package com.slug.core;

import com.slug.GlobalConfig;
import com.slug.utils.PropsUtils;

import java.util.Properties;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public class ConfigHandler {


    private static final Properties configProps = PropsUtils.loadProps(GlobalConfig.CONFIG_PROPS);


    public static String getString(String key) {

        return PropsUtils.getString(configProps, key);

    }

    public static String getString(String key, String defaultValue) {

        return PropsUtils.getString(configProps, key, defaultValue);

    }


}
