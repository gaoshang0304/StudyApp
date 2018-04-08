package com.daydream.studyapp.mvp.contract;

import com.daydream.studyapp.mvp.base.BasePresenter;
import com.daydream.studyapp.mvp.base.BaseView;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;

import java.util.List;

/**
 * 首页数据
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface HomeContract {

    interface View extends BaseView {

        void showContent(HomeDataBean homeDataBean);

        void showMoreContent(HomeDataBean list);
    }

    interface Presenter extends BasePresenter<View> {

        void getHomeData();

        void getHomeMoreData();
    }

}
