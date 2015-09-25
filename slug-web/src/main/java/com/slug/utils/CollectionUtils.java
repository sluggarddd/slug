package com.slug.utils;

import java.util.Collection;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class CollectionUtils {


    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}
