package com.slug.utils;

import com.slug.GlobalConfig;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * encode and decode tools
 *
 * @author zhw
 * @version 0.1  15/9/29
 */
public class CodecUtils {


    /**
     * encode url
     *
     * @param str
     * @return
     */
    public static String encodeURL(String str) {

        String target;

        try {
            target = URLEncoder.encode(str, GlobalConfig.CHARACTER_ENCODING);

        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

        return target;

    }


    /**
     * decode URL
     *
     * @param str
     * @return
     */
    public static String decodeURL(String str) {
        String target;
        try {

            target = URLDecoder.decode(str, GlobalConfig.CHARACTER_ENCODING);
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

        return target;

    }


}
