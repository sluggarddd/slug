package com.slug.datasource;

import javax.sql.DataSource;

/**
 * datasource factory
 *
 * @author zhw
 * @version 0.1  15/11/12
 */
public interface DataSourceFactory {

    /**
     * obtain datasource
     *
     * @return
     */
    DataSource getDataSource();


}
