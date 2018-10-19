package com.androidapp.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    public static boolean isNumber(Object obj) {
        return obj instanceof Integer;
    }

    public static boolean isArray(Object obj) {
        return obj instanceof ArrayList;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isMobile(String str) {
        String regExp = "^(((13)|(14)|(15)|(17)|(18))+\\d{9})$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * @param fixedPhone
     * @return
     */
    public static boolean isFixedPhone(String fixedPhone) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (fixedPhone.length() > 9) {
            m = p1.matcher(fixedPhone);
            b = m.matches();
        } else {
            m = p2.matcher(fixedPhone);
            b = m.matches();
        }
        return b;
    }

    public static boolean isPhone(String phone) {
        return isMobile(phone) || isFixedPhone(phone);
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postCode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isPostCode(String postCode) {
        String reg = "[1-9]\\d{5}";
        return Pattern.matches(reg, postCode);
    }
}
