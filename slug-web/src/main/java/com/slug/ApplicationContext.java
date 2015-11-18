package com.slug;

import com.slug.core.scanner.ClassScanner;
import com.slug.core.ConfigHandler;
import com.slug.core.scanner.DefaultClassScanner;
import com.slug.dao.DataAccessor;
import com.slug.dao.impl.DefaultDataAccessor;
import com.slug.datasource.DataSourceFactory;
import com.slug.datasource.impl.DefaultDataSourceFactory;
import com.slug.servlet.DefaultHandlerException;
import com.slug.servlet.DefaultHandlerInvoker;
import com.slug.servlet.DefaultHandlerMapping;
import com.slug.servlet.DefaultViewResolver;
import com.slug.servlet.handler.HandlerException;
import com.slug.servlet.handler.HandlerInvoker;
import com.slug.servlet.handler.HandlerMapping;
import com.slug.servlet.render.ViewResolver;
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


    private static final String HANDLER_MAPPING = "com.slug.HandlerMapping";

    private static final String HANDLER_INVOKER = "com.slug.HandlerInvoker";

    private static final String CLASS_SCANNER = "com.slug.ClassScanner";

    private static final String VIEW_RESOLVER = "com.slug.ViewResolver";

    private static final String HANDLER_EXCEPTION = "com.slug.HandlerException";

    private static final String DATA_ACCESSOR = "com.slug.dataAccessor";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "com.slug.dataSourceFactory";

    /**
     * get handler mapping
     *
     * @return
     */
    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }


    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_INVOKER, DefaultHandlerInvoker.class);
    }

    public static HandlerException getHandlerException() {
        return getInstance(HANDLER_EXCEPTION, DefaultHandlerException.class);
    }


    /**
     * get ViewResolver
     *
     * @return
     */
    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, DefaultViewResolver.class);
    }

    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    public static DataAccessor getDataAccessor() {
        return getInstance(DATA_ACCESSOR, DefaultDataAccessor.class);
    }

    public static DataSourceFactory getDataSourceFactory() {
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
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
