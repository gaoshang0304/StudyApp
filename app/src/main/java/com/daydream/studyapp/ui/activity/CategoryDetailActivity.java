package com.daydream.studyapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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
    @BindView(R.id.category_detail_toolbar)
    Toolbar toolBar;
    @BindView(R.id.category_detail_rv_content)
    RecyclerView rvContent;

    private CategoryBean item;
    private List<ItemListBean> mList = new ArrayList<>();
    private CategoryDetailAdapter mAdapter;
    private IssueListBean mBean;

    @Override
    public CategoryDetailPresenter initPresenter() {
        return new CategoryDetailPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String extra = getIntent().getStringExtra(Constants.CATEGORY_DETAIL_ITEM);
        if (extra != null) {
            item = JsonUtil.parseJsonToBean(extra, CategoryBean.class);
            ImageLoader.loadImage(this, item.getHeaderImage(), imageDrop);
            toolBar.setTitle("#" + item.getName());
        }
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
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
        //mPresenter.getMoreCategoryDetail(mBean.getNextPageUrl());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_detail;
    }

    @Override
    public void showCategoryDetail(IssueListBean item) {
        mBean = item;
        mList.addAll(item.getItemList());
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void showMoreCategoryDetail(IssueListBean item) {

    }

    @Override
    public void onItemClickListener(ItemListBean item) {

    }
}
