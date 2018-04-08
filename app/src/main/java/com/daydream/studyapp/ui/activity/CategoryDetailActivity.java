package com.daydream.studyapp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.CategoryDetailAdapter;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.mvp.base.BaseMvpActivity;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;
import com.daydream.studyapp.mvp.contract.CategoryDetailContract;
import com.daydream.studyapp.mvp.presenter.CategoryDetailPresenter;
import com.daydream.studyapp.util.JsonUtil;
import com.daydream.studyapp.util.glide.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 分类详情
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-04
 */

public class CategoryDetailActivity extends BaseMvpActivity<CategoryDetailPresenter> implements
                                                                                     CategoryDetailContract.View, CategoryDetailAdapter.OnItemClickListener {
    @BindView(R.id.category_detail_backdrop)
    ImageView imageDrop;
    @BindView(R.id.category_detail_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.category_detail_toolbar)
    Toolbar toolBar;
    @BindView(R.id.category_detail_tv_category_desc)
    TextView tvCategoryDesc;
    @BindView(R.id.category_detail_rv_content)
    RecyclerView rvContent;

    private CategoryBean item;
    private List<ItemListBean> mList = new ArrayList<>();
    private CategoryDetailAdapter mAdapter;
    private boolean loadingMore;

    @Override
    public CategoryDetailPresenter initPresenter() {
        return new CategoryDetailPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String extra = getIntent().getStringExtra(Constants.CATEGORY_DETAIL_ITEM);
        if (extra != null) {
            item = JsonUtil.parseJsonToBean(extra, CategoryBean.class);
            ImageLoader.loadImage(this, item.getHeaderImage(), imageDrop);
            collapsingToolbar.setTitle(item.getName());
            collapsingToolbar.setExpandedTitleColor(Color.WHITE); //设置还没收缩时状态下字体颜色
            collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK); //设置还没收缩时状态下字体颜色
        }
        tvCategoryDesc.setText("#" + item.getDescription() + "#");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rvContent.setLayoutManager(llm);
        rvContent.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JCVideoPlayerStandard jcVideoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.jc_video_player);
                jcVideoPlayer.startVideo();
            }
        });
        mAdapter = new CategoryDetailAdapter(this, mList);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        showProgressDialog("加载中...");

        mPresenter.getCategoryDetailList(item.getId());
        //自动加载更多
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemCount = recyclerView.getLayoutManager().getItemCount();
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = layoutManager.findLastVisibleItemPosition();
                if (!loadingMore && position == (itemCount - 1)) {
                    loadingMore = true;
                    mPresenter.getMoreCategoryDetail();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_detail;
    }

    @Override
    public void showCategoryDetail(IssueListBean item) {
        loadingMore = false;
        mList.addAll(item.getItemList());
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void onItemClickListener(ItemListBean item) {

    }
}
