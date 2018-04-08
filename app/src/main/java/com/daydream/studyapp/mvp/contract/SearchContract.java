package com.daydream.studyapp.mvp.contract;

import com.daydream.studyapp.mvp.base.BasePresenter;
import com.daydream.studyapp.mvp.base.BaseView;

import java.util.List;

/**
 * 搜索
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-08
 */

public interface SearchContract {

    interface View extends BaseView {
        void showHotSearch(List<String> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getHotSearch();
    }

}
