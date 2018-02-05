package com.daydream.studyapp.mvp.base;

/**
 * @author gjc
 * @version 1.0.0
 * @since 2017-12-22
 *
 * view 的一些通用状态接口
 */

public interface IView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 隐藏键盘
     */
    void hideKeybord();

    /**
     * 显示toast消息
     *
     * @param msg 要显示的toast消息字符串
     */
    void showToast(String msg);

}
