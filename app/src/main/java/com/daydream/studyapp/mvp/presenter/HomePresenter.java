package com.daydream.studyapp.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;
import com.daydream.studyapp.mvp.contract.HomeContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
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
                        Iterator<ItemListBean> iterator = itemList.iterator();
                        while (iterator.hasNext()) {
                            ItemListBean bean = iterator.next();
                            if ("banner2".equals(bean.getType()) || "horizontalScrollCard".equals(bean.getType())) {
                                iterator.remove();
                            }
                            iterator.next();
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
                        Iterator<ItemListBean> iterator = newItemList.iterator();
                        while (iterator.hasNext()) {
                            ItemListBean bean = iterator.next();
                            if ("banner2".equals(bean.getType()) || "horizontalScrollCard".equals(bean.getType())) {
                                iterator.remove();
                            }
                            iterator.next();
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
                        Iterator<ItemListBean> iterator = newItemList.iterator();
                        while (iterator.hasNext()) {
                            ItemListBean bean = iterator.next();
                            if ("banner2".equals(bean.getType()) || "horizontalScrollCard".equals(bean.getType())) {
                                iterator.remove();
                            }
                            iterator.next();
                        }
                        mView.showMoreContent(homeDataBean);
                    }
                }));
        }
    }

}
