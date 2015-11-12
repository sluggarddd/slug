package com.slug.orm;

/**
 * provide operation between database and class
 *
 * @author zhw
 * @version 0.1  15/11/10
 */
public class DataSet {


    /**
     * query for single object,and turn into class
     *
     * @param entityClass
     * @param condition
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T select(Class<T> entityClass, String condition, Object... params) {

        return null;
    }


}
