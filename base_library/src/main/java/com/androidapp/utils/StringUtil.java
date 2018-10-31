package com.androidapp.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class StringUtil {

    public static DecimalFormat sFormat = new DecimalFormat("#.0");

    public static int parseInt(String text) {
        long result = parseLong(text);
        return result > Integer.MAX_VALUE ? 0 : (int) result;
    }

    public static int parseInt(String text, int def) {
        return (int) parseDouble(text, def);
    }

    public static double parseDouble(String text) {
        return parseDouble(text, 0);
    }

    public static double parseDouble(String text, double def) {
        double result = def;
        try {
            if (TextUtils.isEmpty(text)) {
                return def;
            }
            if (text.indexOf(".") != -1)
                result = Double.valueOf(text);
            else
                result = Long.valueOf(text);
        } catch (Exception e) {

        }
        return result;
    }

    public static long parseLong(String text) {
        return parseLong(text, 0);
    }

    public static long parseLong(String text, long def) {
        return (long) parseDouble(text, def);
    }

    public static float parseFloat(String text) {
        return parseFloat(text, 0);
    }

    public static float parseFloat(String text, float def) {
        return (float) parseDouble(text, def);
    }
}
