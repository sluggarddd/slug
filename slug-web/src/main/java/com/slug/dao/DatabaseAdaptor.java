package com.slug.dao;

import com.slug.ApplicationContext;
import com.slug.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * encapsulation database operation
 *
 * @author zhw
 * @version 0.1  15/11/12
 */
public class DatabaseAdaptor {


    /**
     * define a local thread，every thread owns its connection
     */
    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    /**
     * get datasource factory
     */
    private static final DataSourceFactory dataSourceFactory = ApplicationContext.getDataSourceFactory();


    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }

}
