package com.slug.utils;

/**
 * @author zhw
 * @version 0.1  15/9/28
 */
public class ArrayUtils {

    public static boolean isEmpty(Object[] array) {


        return array == null || array.length == 0;
    }

    /**
     * 连接数组
     */
    public static Object[] concat(Object[] array1, Object[] array2) {
        return org.apache.commons.lang.ArrayUtils.addAll(array1, array2);
    }

}
