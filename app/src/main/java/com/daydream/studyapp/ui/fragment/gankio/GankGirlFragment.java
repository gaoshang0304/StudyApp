package com.daydream.studyapp.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.GankListAdapter;
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
    private final String mCategory = "Android";
    private int mPage = 1;
    private boolean loadingMore;
    private GankListAdapter mAdapter;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(layoutManager);
        rvContent.addOnScrollListener(new MyRvScrollListener());
        srlRefresh.setOnRefreshListener(this);
        srlRefresh.setRefreshing(true);
        mAdapter = new GankListAdapter(mContext, mBean, R.layout.item_gank_data);

        mPresenter.getGankContent(mCategory, mPage);
    }

    @Override
    public void showGankContent(List<GankListBean> list) {
        mBean.clear();
        mBean.addAll(list);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        if (srlRefresh.isRefreshing()) {
            srlRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showMoreGankData(List<GankListBean> list) {
        if (mAdapter != null) {
            mAdapter.addMoreData(list);
        }
    }

    @Override
    public void onRefresh() {

    }

    private class MyRvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int itemCount = recyclerView.getLayoutManager().getItemCount();
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = layoutManager.findLastVisibleItemPosition();
            if (!loadingMore && position == (itemCount - 1)) {
                loadingMore = true;
                mPresenter.getGankContent(mCategory, ++mPage);
            }
        }
    }
}
