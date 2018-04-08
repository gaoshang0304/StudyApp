package com.daydream.studyapp.mvp.contract;

import com.daydream.studyapp.mvp.base.BasePresenter;
import com.daydream.studyapp.mvp.base.BaseView;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;

import java.util.List;

/**
 * @author gjc
 * @version ;
 * @since 2018-04-08
 */

public interface GankCategoryContract {

    interface View extends BaseView {

        void showGankContent(List<GankListBean> list);

        void showMoreGankData(List<GankListBean> list);
    }

    interface Presenter extends BasePresenter<View> {

        void getGankContent(String category, int page);

    }

}
