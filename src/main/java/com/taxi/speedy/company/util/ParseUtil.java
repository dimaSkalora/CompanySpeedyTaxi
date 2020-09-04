package com.taxi.speedy.company.util;

import org.springframework.util.StringUtils;

public class ParseUtil {
    public static Integer parseInteger(String str) {
        return StringUtils.isEmpty(str) ? null : Integer.parseInt(str);
    }

    public static String parseString(String str) {
        return StringUtils.isEmpty(str) ? null : str;
    }
}
