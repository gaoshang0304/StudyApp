package com.daydream.studyapp.mvp.base;

import com.daydream.studyapp.util.ToastUtil;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected T mPresenter = initPresenter();

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.showToast(mContext, msg);
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract T initPresenter();
}
