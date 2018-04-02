package com.daydream.studyapp.mvp.base;

/**
 * Created by gjc on 2018/04/02.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
