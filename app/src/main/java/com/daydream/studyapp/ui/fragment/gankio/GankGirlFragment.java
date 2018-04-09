package com.daydream.studyapp.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.GankGirlAdapter;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;
import com.daydream.studyapp.mvp.contract.GankCategoryContract;
import com.daydream.studyapp.mvp.presenter.GankPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 福利
 *
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankGirlFragment extends BaseMvpFragment<GankPresenter> implements GankCategoryContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_gankio_girl)
    RecyclerView rvContent;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private String mCategory = "";
    private int mPage = 1;
    private boolean loadingMore;
    private GankGirlAdapter mAdapter;
    private List<GankListBean> mBean = new ArrayList<>();

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
        String category = getArguments().getString("category");
        if (category != null && !TextUtils.isEmpty(category)) {
            mCategory = category;
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        rvContent.addOnScrollListener(new MyRvScrollListener());
        srlRefresh.setOnRefreshListener(this);
        srlRefresh.setRefreshing(true);
        mAdapter = new GankGirlAdapter(mContext, mBean, R.layout.item_gank_girl);
        rvContent.setAdapter(mAdapter);

        mPresenter.getGankContent(mCategory, mPage);
    }

    @Override
    public void showGankContent(List<GankListBean> list) {
        loadingMore = false;
        mAdapter.addMoreData(list);
        if (srlRefresh.isRefreshing()) {
            srlRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mPresenter.getGankContent(mCategory, mPage);
    }

    private class MyRvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int itemCount = recyclerView.getLayoutManager().getItemCount();
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            int[] itemPositions = layoutManager.findLastVisibleItemPositions(null);
            int lastPosition = Math.max(itemPositions[0], itemPositions[1]);
            if (!loadingMore && lastPosition == (itemCount - 3)) {
                loadingMore = true;
                mPresenter.getGankContent(mCategory, ++mPage);
            }
        }
    }
}
