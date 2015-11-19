package com.slug.datasource.impl;

import com.slug.core.ConfigHandler;
import com.slug.datasource.DataSourceFactory;

import javax.sql.DataSource;

/**
 * @author zhw
 * @version 0.1  15/11/12
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {

    protected final String driver = ConfigHandler.getString("com.slug.jdbc.driver");
    protected final String url = ConfigHandler.getString("com.slug.jdbc.url");
    protected final String username = ConfigHandler.getString("com.slug.jdbc.username");
    protected final String password = ConfigHandler.getString("com.slug.jdbc.password");


    @Override
    public DataSource getDataSource() {
        //create dataSource
        T ds = createDataSource();

        //set basic property
        setDriver(ds, driver);
        setUrl(ds, url);
        setUsername(ds, username);
        setPassword(ds, password);
        //set advanced config
        setAdvancedConfig(ds);
        return ds;
    }


    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);
}
