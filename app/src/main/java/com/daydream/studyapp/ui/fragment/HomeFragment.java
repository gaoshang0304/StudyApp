package com.daydream.studyapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.contract.HomeContract;
import com.daydream.studyapp.mvp.presenter.HomePresenter;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    public void showContent(HomeDataBean homeDataBean) {

    }

    @Override
    public void showMoreContent() {

    }
}
