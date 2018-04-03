package com.daydream.studyapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.CategoryAdapter;
import com.daydream.studyapp.mvp.base.BaseMvpFragment;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.contract.CategoryContract;
import com.daydream.studyapp.mvp.presenter.CategoryPresenter;
import com.daydream.studyapp.weight.RvSpaceItemDec;

import java.util.List;

import butterknife.BindView;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class CategoryFragment extends BaseMvpFragment<CategoryPresenter> implements CategoryContract.View {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

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
        toolBar.setTitle("分类");
        GridLayoutManager glm = new GridLayoutManager(mContext, 2);
        rvCategory.setLayoutManager(glm);
        rvCategory.addItemDecoration(new RvSpaceItemDec(50));

        mPresenter.getCategory();
    }

    @Override
    public void showCategory(List<CategoryBean> list) {
        rvCategory.setAdapter(new CategoryAdapter(getActivity(), list));
    }

}
