package com.wgl.android.library.commonutils;

import java.text.DecimalFormat;

/**
 * des:金钱
 */
public class MoneyUtil {
    public static String MoneyFomatWithTwoPoint(double d){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}
