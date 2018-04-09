package com.daydream.studyapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.CategoryAdapter;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.contract.CategoryContract;
import com.daydream.studyapp.mvp.presenter.CategoryPresenter;
import com.daydream.studyapp.ui.activity.CategoryDetailActivity;
import com.daydream.studyapp.util.JsonUtil;
import com.daydream.studyapp.weight.RvSpaceItemDec;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class CategoryFragment extends BaseMvpFragment<CategoryPresenter> implements CategoryContract.View, CategoryAdapter.OnItemClickListener {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.toolbar_title)
    TextView tv_title;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    private CategoryAdapter mAdapter;
    private ArrayList<CategoryBean> mList = new ArrayList<>();

    @Override
    public CategoryPresenter initPresenter() {
        return new CategoryPresenter(getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        tv_title.setText("分类");
        GridLayoutManager glm = new GridLayoutManager(mContext, 2);
        rvCategory.setLayoutManager(glm);
        rvCategory.addItemDecoration(new RvSpaceItemDec(50));
        mAdapter = new CategoryAdapter(getActivity(), mList);
        rvCategory.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        showProgressDialog("加载中...");
        mPresenter.getCategory();
    }

    @Override
    public void showCategory(List<CategoryBean> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void onItemClickListener(CategoryBean item, View itemView) {
        String json = JsonUtil.parseBeanToString(item);
        Intent intent = new Intent(mActivity, CategoryDetailActivity.class);
        intent.putExtra(Constants.CATEGORY_DETAIL_ITEM, json);
        startActivity(intent);

    }

}
