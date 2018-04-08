package com.daydream.studyapp.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;
import com.daydream.studyapp.mvp.contract.GankCategoryContract;
import com.daydream.studyapp.mvp.presenter.GankPresenter;

import java.util.List;

/**
 * ios
 *
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankIosFragment extends BaseMvpFragment<GankPresenter> implements GankCategoryContract.View{

    @Override
    public GankPresenter initPresenter() {
        return new GankPresenter(mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_common;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showGankContent(List<GankListBean> list) {

    }

    @Override
    public void showMoreGankData(List<GankListBean> list) {

    }
}
