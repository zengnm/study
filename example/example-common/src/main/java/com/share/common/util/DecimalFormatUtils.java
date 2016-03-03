package com.share.common.util;

import java.text.DecimalFormat;

public class DecimalFormatUtils {


    public static String format(double d) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(d);
    }

    public static String format(long i) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(i);
    }

    public static String format(double d, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(d);
    }

    public static String format(long i, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(i);
    }

    public static void main(String[] args) {
        System.out.println(DecimalFormatUtils.format(-23423434234.3434));
        long i = 34234234234234l;
        System.out.println(DecimalFormatUtils.format(i));
    }
}


