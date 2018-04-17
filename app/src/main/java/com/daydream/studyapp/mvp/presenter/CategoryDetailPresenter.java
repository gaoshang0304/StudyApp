package com.daydream.studyapp.mvp.presenter;

import android.content.Context;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.contract.CategoryDetailContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;
import com.daydream.studyapp.weight.CommonSubscriber;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-04
 */

public class CategoryDetailPresenter extends RxPresenter<CategoryDetailContract.View> implements CategoryDetailContract.Presenter {

    private Context mContext;
    private String nextPageUrl = null;

    public CategoryDetailPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getCategoryDetailList(long id) {
        addSubscribe(DataManager.getInstance(mContext).getCategoryDetail(id)
            .compose(RxUtil.<IssueListBean>rxSchedulerHelper())
            .subscribeWith(new CommonSubscriber<IssueListBean>(mView, "加载数据失败", true) {

                @Override
                public void onNext(IssueListBean issueListBean) {
                    nextPageUrl = issueListBean.getNextPageUrl();
                    mView.showCategoryDetail(issueListBean);
                }
            }));
    }

    @Override
    public void getMoreCategoryDetail() {
        addSubscribe(DataManager.getInstance(mContext).getMoreCategoryDetail(nextPageUrl)
                .compose(RxUtil.<IssueListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<IssueListBean>(mView, "加载更多数据失败") {
                    @Override
                    public void onNext(IssueListBean issueListBean) {
                        mView.showCategoryDetail(issueListBean);
                    }
                }));
    }
}
