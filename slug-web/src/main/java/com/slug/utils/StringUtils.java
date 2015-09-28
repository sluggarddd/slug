package com.slug.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhw
 * @version 0.1  15/9/22
 */
public class StringUtils {


    /**
     * Check the String is empty or not
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }


    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }


    /**
     * check the str has text or not
     * only contain the white space will return false
     *
     * @param str
     * @return
     */
    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;

    }


    public static String replaceAll(String str, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    public static String isEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }
}
