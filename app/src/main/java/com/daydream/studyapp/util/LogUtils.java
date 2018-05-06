package com.daydream.studyapp.util;

import android.util.Log;

import com.daydream.studyapp.http.AppConfig;

import java.util.logging.Logger;

/**
 * 日志工具
 *
 * @author gjc
 * @version 1.0.0
 * @since 2018-05-04
 */
public class LogUtils {

    private static final String TAG = "zjdg";

    public static void d(String msg) {
        if (AppConfig.isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String msg) {
        if (AppConfig.isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (AppConfig.isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String msg) {
        if (AppConfig.isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void v(String msg) {
        if (AppConfig.isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.v(tag, msg);
        }
    }


    public static void wtf(String msg) {
        if (AppConfig.isDebug) {
            Log.wtf(TAG, msg);
        }
    }

    public static void wtf(String tag, String msg) {
        if (AppConfig.isDebug) {
            Log.wtf(tag, msg);
        }
    }

}