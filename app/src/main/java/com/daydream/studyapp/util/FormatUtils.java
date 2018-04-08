package com.daydream.studyapp.util;

/**
 * Created by dayDream on 2018/4/6.
 */

public class FormatUtils {

    /**
     * 格式化视频时长 分'秒''
     */
    public static String durationFormat(long duration) {
        long minute = duration / 60;
        long second = duration % 60;
        String result = "";
        if (minute <= 9) {
            if (second <= 9) {
                result = "0" + minute + "' " + "0" + second + "''";
            } else {
                result = "0" + minute + "' " + second + "''";
            }
        } else {
            if (second <= 9) {
                result = minute + "' " + "0" + second + "''";
            } else {
                result = minute + "' " + second + "''";
            }
        }
        return result;
    }
}
