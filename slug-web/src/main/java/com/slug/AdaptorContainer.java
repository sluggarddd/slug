package com.slug;

import com.slug.utils.ClassUtils;

/**
 * the container of adaptor
 *
 * @author zhw
 * @version 0.1  15/10/5
 */
public class AdaptorContainer {


    public static void init() {

        Class<?>[] classes = {};


        for (Class<?> cls : classes) {
            ClassUtils.loadClass(cls.getName());
        }
    }


}
