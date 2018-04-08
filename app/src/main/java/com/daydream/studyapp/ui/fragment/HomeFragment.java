package com.daydream.studyapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daydream.studyapp.R;
import com.daydream.studyapp.kotlin.HomeAdapter;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;
import com.daydream.studyapp.mvp.contract.HomeContract;
import com.daydream.studyapp.mvp.presenter.HomePresenter;
import com.daydream.studyapp.ui.activity.SearchActivity;
import com.daydream.studyapp.weight.recyclerview.MultiItemSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view_content)
    RecyclerView recyclerViewContent;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rel_toolbar)
    RelativeLayout relToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<ItemListBean> mList = new ArrayList<>();
    public static final int ITEM_TYPE_BANNER = 1;
    public static final int ITEM_TYPE_TEXT_HEADER = 2;
    public static final int ITEM_TYPE_CONTENT = 3;
    private LinearLayoutManager llm;
    private boolean loadingMore;
    private HomeAdapter mAdapter;
    private IssueListBean listBean;
    private SimpleDateFormat simpleDateFormat;
    private boolean isRefresh;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout.measure(0, 0);
        swipeRefreshLayout.setRefreshing(true);
        simpleDateFormat = new SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH);
        swipeRefreshLayout.setOnRefreshListener(this);
        llm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerViewContent.setLayoutManager(llm);
        recyclerViewContent.addOnScrollListener(new MyRvScrollListener());


        mPresenter.getHomeData();

    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(mContext);
    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, SearchActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<View, String>(ivSearch, "IMG_TRANSITION");
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, pair);
            ActivityCompat.startActivity(mActivity, intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
            mActivity.overridePendingTransition(R.anim.activity_finish_zoom_in, R.anim.activity_finish_zoom_out);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getHomeData();
    }

    @Override
    public void showContent(HomeDataBean homeDataBean) {
        mList.clear();
        listBean = homeDataBean.getIssueList().get(0);
        mList.addAll(listBean.getItemList());
//        MultiItemSupport multiItemSupport = setMultiItemSupport(listBean);

        mAdapter = new HomeAdapter(mContext, mList);
        mAdapter.setBannerSize(listBean.getCount());
        recyclerViewContent.setAdapter(mAdapter);
        recyclerViewContent.setItemAnimator(new DefaultItemAnimator());
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showMoreContent(HomeDataBean bean) {
        loadingMore = false;
        if (mAdapter != null) {
            mList.addAll(bean.getIssueList().get(0).getItemList());
            mAdapter.addItemData(mList);
        }
    }

    private class MyRvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == recyclerView.SCROLL_STATE_IDLE) {
                int childCount = recyclerView.getChildCount();
                int itemCount = llm.getItemCount();
                int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition + childCount == itemCount) {
                    if (!loadingMore) {
                        loadingMore = true;
                        mPresenter.getHomeMoreData();
                    }
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int itemPosition = llm.findFirstVisibleItemPosition();
            if (itemPosition == 0) {
                //背景设置为透明
                toolbar.setBackgroundColor(mContext.getResources().getColor(R.color.color_translucent));
                ivSearch.setImageResource(R.drawable.ic_action_search_white);
                tvHeaderTitle.setText("");
            } else {
                if (mList.size() > 1) {
                    toolbar.setBackgroundColor(mContext.getResources().getColor(R.color.color_title_bg));
                    ivSearch.setImageResource(R.drawable.ic_action_search_black);
                    ItemListBean item = mList.get(itemPosition + listBean.getCount() - 1);
                    if (item.getType().equals("textHeader")) {
                        tvHeaderTitle.setText(item.getData().getText());
                    } else {
                        tvHeaderTitle.setText(simpleDateFormat.format(item.getData().getDate()));
                    }
                }
            }
        }
    }

    private MultiItemSupport setMultiItemSupport(final IssueListBean listBean) {
        MultiItemSupport<ItemListBean> mIsupport = new MultiItemSupport<ItemListBean>() {
            @Override
            public int getLayoutId(int itemType) {
                int layoutId = 0;
                switch (itemType) {
                    case ITEM_TYPE_BANNER:
                        layoutId = R.layout.item_home_banner;
                        break;
                    case ITEM_TYPE_TEXT_HEADER:
                        layoutId = R.layout.item_home_text_header;
                        break;
                    case ITEM_TYPE_CONTENT:
                        layoutId = R.layout.item_home_content;
                        break;

                }
                return layoutId;
            }

            @Override
            public int getItemViewType(int position, ItemListBean itemListBean) {
                if (0 == position) {
                    return ITEM_TYPE_BANNER;
                } else if (mList.get((position + listBean.getCount() - 1)).getType().equals("textHeader")) {
                    return ITEM_TYPE_TEXT_HEADER;
                } else {
                    return ITEM_TYPE_CONTENT;
                }
            }
        };
        return mIsupport;
    }
}
