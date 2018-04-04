package com.daydream.studyapp.mvp.presenter;

import android.content.Context;

import com.daydream.studyapp.mvp.base.BaseView;
import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.contract.HomeContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;
import com.daydream.studyapp.weight.CommonSubscriber;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private Context mContext;

    public HomePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getHomeData() {
        addSubscribe(DataManager.getInstance(mContext).getHomeData()
                .compose(RxUtil.<HomeDataBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<HomeDataBean>(mView) {
                    @Override
                    public void onNext(HomeDataBean homeDataBean) {
                        mView.showContent(homeDataBean);
                    }
                }));
    }

    @Override
    public void getHomeMoreData() {

    }

}
