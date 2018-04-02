package com.daydream.studyapp.mvp.presenter;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.contract.CategoryContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;
import com.daydream.studyapp.weight.CommonSubscriber;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class CategoryPresenter extends RxPresenter<CategoryContract.View> implements CategoryContract.Presenter {

    private DataManager mDataManager;

    public CategoryPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getCategory() {
        addSubscribe(mDataManager.getCategoryData()
                .compose(RxUtil.<List<CategoryBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<CategoryBean>>(mView) {
                    @Override
                    public void onNext(List<CategoryBean> list) {
                        mView.showCategory(list);
                    }
        }));
    }
}
