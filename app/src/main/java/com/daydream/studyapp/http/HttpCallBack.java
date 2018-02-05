package com.daydream.studyapp.http;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-20
 */
public abstract class HttpCallBack {

    /**
     * 开始回调
     */
    void onStart() {};

    /**
     * 成功回调
     */
    public abstract void onSuccess(String data);

    /**
     * 失败回调
     */
    public abstract void onError(String msg);
}
