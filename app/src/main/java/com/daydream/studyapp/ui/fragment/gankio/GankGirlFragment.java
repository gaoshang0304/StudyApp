package com.daydream.studyapp.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.GankGirlsAdapter;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.http.HttpCallBack;
import com.daydream.studyapp.http.HttpUtils;
import com.daydream.studyapp.service.base.fragment.BaseFragment;
import com.daydream.studyapp.service.entity.gankio.GankGirlItemBean;
import com.daydream.studyapp.service.entity.gankio.GankGirlListBean;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankGirlFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_gankio_girl)
    RecyclerView rvGankioGirl;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srl_refresh;

    private int mPage = 1;
    private GankGirlsAdapter mAdapter;
    private List<GankGirlItemBean> mBean;
    private int visibleThreshold = 5;
    private boolean canLoadMore = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_girl;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        srl_refresh.setOnRefreshListener(this);
        rvGankioGirl.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rvGankioGirl.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int[] positions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                int lastPosition = findMax(positions);
                Log.i("lastPosition --> ", lastPosition + "");
                Log.i("itemCount  --> ", itemCount + " ");
                //如果当前不是正在加载更多，并且到了该加载更多的位置，加载更多。
                if ((lastPosition >= (itemCount - visibleThreshold))) {
                    if (canLoadMore) {
                        ++mPage;
                        loadData();
                    }
                }

            }
        });
        loadData();
    }

    @Override
    public void onRefresh() {
        srl_refresh.setRefreshing(true);
        mPage = 1;
        loadData();
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void handleData(List<GankGirlItemBean> item) {
        if (item == null) {

        }
        if (mPage == 1) {
            mBean = item;
            mAdapter = new GankGirlsAdapter(getContext(), mBean);
            rvGankioGirl.setAdapter(mAdapter);
        } else {
            mBean.addAll(item);
            mAdapter.notifyDataSetChanged();
        }
        srl_refresh.setRefreshing(false);

    }

    /**
     * 请求数据
     */
    private void loadData() {
        String url = Constants.GANK_HOST + "api/data/福利/20/" + mPage;
        HttpUtils.doGet(url, new HttpCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.e("tag", data);
                GankGirlListBean listBean = new Gson().fromJson(data, GankGirlListBean.class);
                if (!listBean.isError()) {
                    handleData(listBean.getResults());
                }
            }

            @Override
            public void onError(String msg) {
                handleData(null);
            }
        });
    }

}
