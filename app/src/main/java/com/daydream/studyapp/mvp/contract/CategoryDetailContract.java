package com.daydream.studyapp.mvp.contract;

import com.daydream.studyapp.mvp.base.BasePresenter;
import com.daydream.studyapp.mvp.base.BaseView;
import com.daydream.studyapp.mvp.bean.IssueListBean;

/**
 * 分类详情
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-04
 */

public interface CategoryDetailContract {

    interface View extends BaseView{

       void showCategoryDetail(IssueListBean bean);

    }

    interface Presenter extends BasePresenter<View> {

        void getCategoryDetailList(long id);

        void getMoreCategoryDetail();
    }

}
