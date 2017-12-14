package com.daydream.studyapp.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 吐司工具
 */
public class ToastUtil {

    public static Toast mToast;

    /**
     * 自定义图片toast
     *
     * @param context
     * @param resId   背景图片
     */
    public static void showImage(Context context, int resId) {
        try {
            cancelToast();
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(resId);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, -60);
            mToast.setView(imageView);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义布局toast
     *
     * @param context
     * @param layoutId
     */
    public static void showLayout(Context context, int layoutId) {
        try {
            cancelToast();
            View view = View.inflate(context, layoutId, null);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, -60);
            mToast.setView(view);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印普通toast方法
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg, int duration) {
        try {
            cancelToast();
            mToast = Toast.makeText(context, msg, duration);
            mToast.setGravity(Gravity.CENTER, 0, -30);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印普通toast方法
     *
     * @param context
     * @param resId
     */
    public static void showToast(Context context, int resId, int duration) {
        try {
            cancelToast();
            mToast = Toast.makeText(context, resId, duration);
            mToast.setGravity(Gravity.CENTER, 0, -30);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印普通toast方法
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 打印普通toast方法
     *
     * @param context
     * @param resId
     */
    public static void showToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 关闭toast
     */
    public static void cancelToast() {
        try {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}