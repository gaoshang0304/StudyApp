package com.daydream.studyapp.mvp.base;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    //显示dialog
    void showLoadingDialog(String msg);

    //取消dialog
    void dismissLoadingDialog();

    void showErrorMsg(String msg);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
