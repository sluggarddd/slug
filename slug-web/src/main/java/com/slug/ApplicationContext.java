package com.slug;

import com.slug.core.ConfigHandler;
import com.slug.servlet.exception.DefaultHandlerMapping;
import com.slug.servlet.handler.HandlerMapping;
import com.slug.utils.ObjectUtils;
import com.slug.utils.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public class ApplicationContext {


    private static final Map<String, Object> instances = new ConcurrentHashMap<String, Object>();

    private static final String HANDLER_MAPPING = "com.slug.custom.handler_mapping";

    /**
     * get handler mapping
     *
     * @return
     */
    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    public static <T> T getInstance(String key, Class<T> defaultImplClass) {

        if (instances.containsKey(key)) {
            return (T) instances.get(key);
        }

        //get the class impl from properties
        String implClassName = ConfigHandler.getString(key);

        //if not config in properties then user defaultImpl
        if (StringUtils.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }

        T instance = ObjectUtils.newInstance(implClassName);

        if (instance != null) {
            instances.put(key, instance);
        }
        //return instance
        return instance;

    }


}
