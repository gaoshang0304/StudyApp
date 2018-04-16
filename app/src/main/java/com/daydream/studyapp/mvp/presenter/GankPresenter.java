package com.daydream.studyapp.mvp.presenter;

import android.content.Context;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.gank.ResponseResult;
import com.daydream.studyapp.mvp.contract.GankCategoryContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;

import io.reactivex.functions.Consumer;

/**
 * @author gjc
 * @version ;
 * @since 2018-04-08
 */

public class GankPresenter extends RxPresenter<GankCategoryContract.View> implements GankCategoryContract.Presenter {

    private Context mContext;

    public GankPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getGankContent(String category, final int page) {
        addSubscribe(DataManager.getInstance(mContext).getGankData(category, page)
            .compose(RxUtil.<ResponseResult>rxOtSchedulerHelper())
            .subscribe(new Consumer<ResponseResult>() {
                @Override
                public void accept(ResponseResult result) throws Exception {
                    if (!result.isError()) {
                        if (1 == page) {
                            mView.showGankContent(result.getResults());
                        } else {
                            mView.showGankMoreContent(result.getResults());
                        }
                    }
                }
            }));
    }
}
