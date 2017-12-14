package com.daydream.studyapp.service.manager;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by gjc on 2017/12/13.
 * <p>
 * 用于管理Rxjava 注册订阅和取消订阅
 */

public class RxManager {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();// 管理订阅者者

    public void register(Disposable d) {
        mCompositeDisposable.add(d);
    }

    public void unSubscribe() {
        mCompositeDisposable.dispose();// 取消订阅
    }
}