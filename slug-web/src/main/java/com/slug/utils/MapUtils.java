package com.slug.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public class MapUtils {

    public static boolean isEmpty(Map map) {

        return map == null || map.isEmpty();
    }


    /**
     * 转置 Map
     */
    public static <K, V> Map<V, K> invert(Map<K, V> source) {
        Map<V, K> target = null;
        if (!isEmpty(source)) {
            target = new LinkedHashMap<V, K>(source.size());
            for (Map.Entry<K, V> entry : source.entrySet()) {
                target.put(entry.getValue(), entry.getKey());
            }
        }
        return target;
    }
}
