package com.slug.utils;

/**
 * @author zhw
 * @version 0.1  15/9/23
 */
public abstract class Assert {


    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }
}
