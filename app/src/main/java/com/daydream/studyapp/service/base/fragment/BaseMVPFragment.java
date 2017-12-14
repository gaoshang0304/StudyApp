package com.daydream.studyapp.service.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.daydream.studyapp.service.base.BasePresenter;
import com.daydream.studyapp.service.base.IBaseFragment;
import com.daydream.studyapp.service.base.IBaseModel;
import com.daydream.studyapp.service.base.activity.BaseActivity;
import com.daydream.studyapp.util.ToastUtils;

/**
 * Created by gjc on 2017/12/13.
 * <p>
 * Mvp Fragment基类
 * <p>
 * 实现IBaseView方法、绑定butterknife
 */

public abstract class BaseMVPFragment<P extends BasePresenter, M extends IBaseModel> extends
        BaseFragment implements IBaseFragment {
    public P mPresenter;
    public M mIMode;

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        super.initData();

        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachMV(mIMode, this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    @Override
    public void showWaitDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void hideKeybord() {
        hideSoftInput();
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseActivity) mActivity).startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseActivity) mActivity).startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public boolean isVisiable() {
        return isVisible();
    }

    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}