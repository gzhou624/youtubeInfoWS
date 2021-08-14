package com.demo.util;

import java.util.Collection;
import java.util.Map;

public class AppUtil {
    private AppUtil() {
    }

    public static boolean isNotNull(Object argObj) {
        return !isNull(argObj);
    }

    public static boolean isNull(Object argObj) {
        if (argObj == null) {
            return true;
        }

        if (argObj instanceof String) {
            return ((String) argObj).trim().equals("") || ((String) argObj).trim().equals(" ") || ((String) argObj).trim().equals("null");
        }

        if (argObj instanceof Collection) {

            return ((Collection) argObj).isEmpty();
        }

        if (argObj instanceof Map) {

            return ((Map) argObj).isEmpty();
        }

        return false;
    }

    public static long parseLong(String str) {
        if (isNull(str)) {
            return 0;
        }
        long l;
        try {
            l = Long.parseLong(str);
        } catch (NumberFormatException e) {
            return 0;
        }
        return l;
    }
}
