package com.daydream.studyapp.ui.activity;

import android.os.Bundle;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.base.BaseMvpActivity;
import com.daydream.studyapp.mvp.contract.SearchContract;
import com.daydream.studyapp.mvp.presenter.SearchPresenter;
import com.daydream.studyapp.weight.FlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 搜索页
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-08
 */

public class SearchActivity extends BaseMvpActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter(mContext);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter.getHotSearch();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void showHotSearch(List<String> list) {
        flowLayout.addData(list);
    }
}
