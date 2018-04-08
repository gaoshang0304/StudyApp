package com.daydream.studyapp.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;
import com.daydream.studyapp.mvp.contract.HomeContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;
import com.daydream.studyapp.weight.CommonSubscriber;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private Context mContext;
    private String mNextPageUrl = null;
    private HomeDataBean bannerHomeBean = null;

    public HomePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getHomeData() {
        addSubscribe(DataManager.getInstance(mContext).getHomeData()
                .compose(RxUtil.<HomeDataBean>rxOtSchedulerHelper())
                .flatMap(new Function<HomeDataBean, Observable<HomeDataBean>>() {
                    @Override
                    public Observable<HomeDataBean> apply(HomeDataBean homeDataBean) throws Exception {
                        List<ItemListBean> itemList = homeDataBean.getIssueList().get(0).getItemList();
                        for (int i = 0; i < itemList.size(); i++) {
                            if ("banner2".equals(itemList.get(i).getType()) || "horizontalScrollCard".equals(itemList.get(i).getType())) {
                                itemList.remove(i);
                            }
                        }
                        bannerHomeBean = homeDataBean;
                        Observable<HomeDataBean> moreHomeData = DataManager.getInstance(mContext).getMoreHomeData(homeDataBean.getNextPageUrl());

                        return moreHomeData;
                    }
                })
                .subscribe(new Consumer<HomeDataBean>() {
                    @Override
                    public void accept(HomeDataBean homeDataBean) throws Exception {
                        mNextPageUrl = homeDataBean.getNextPageUrl();
                        List<ItemListBean> newItemList = homeDataBean.getIssueList().get(0).getItemList();
                        for (int i = 0; i < newItemList.size(); i++) {
                            if ("banner2".equals(newItemList.get(i).getType()) || "horizontalScrollCard".equals(newItemList.get(i).getType())) {
                                newItemList.remove(i);
                            }
                        }
                        // 重新赋值 Banner 长度
                        bannerHomeBean.getIssueList().get(0).setCount(bannerHomeBean.getIssueList().get(0).getItemList().size());
                        //赋值过滤后的数据 + banner 数据
                        if (null != bannerHomeBean) {
                            bannerHomeBean.getIssueList().get(0).getItemList().addAll(newItemList);
                        }
                        mView.showContent(bannerHomeBean);
                    }
                })
        );
    }

    @Override
    public void getHomeMoreData() {
        if (null != mNextPageUrl) {
            Log.e("app", "mNextPageUrl == " + mNextPageUrl);
            addSubscribe(DataManager.getInstance(mContext).getMoreHomeData(mNextPageUrl)
                .compose(RxUtil.<HomeDataBean>rxOtSchedulerHelper())
                .subscribe(new Consumer<HomeDataBean>() {
                    @Override
                    public void accept(HomeDataBean homeDataBean) throws Exception {
                        mNextPageUrl = homeDataBean.getNextPageUrl();
                        List<ItemListBean> newItemList = homeDataBean.getIssueList().get(0).getItemList();
                        for (int i = 0; i < newItemList.size(); i++) {
                            if ("banner2".equals(newItemList.get(i).getType()) || "horizontalScrollCard".equals(newItemList.get(i).getType())) {
                                newItemList.remove(i);
                            }
                        }
                        mView.showMoreContent(homeDataBean);
                    }
                }));
        }
    }

}
