package com.daydream.studyapp.mvp.contract;

import com.daydream.studyapp.mvp.base.BasePresenter;
import com.daydream.studyapp.mvp.base.BaseView;
import com.daydream.studyapp.mvp.bean.CategoryBean;

import java.util.List;

/**
 * 分类数据
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface CategoryContract {

    interface View extends BaseView {

        void showCategory(List<CategoryBean> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getCategory();

    }

}
