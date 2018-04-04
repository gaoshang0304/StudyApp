package com.daydream.studyapp.mvp.http;

import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.bean.HomeDataBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface HttpHelper {

    /**
     * 获取首页数据
     */
    Flowable<HomeDataBean> getHomeData();

    /**
     * 分类数据
     */
    Flowable<List<CategoryBean>> getCategoryData();

    Flowable<IssueListBean> getCategoryDetail(long id);

    Flowable<IssueListBean> getMoreCategoryDetail(String path);
}
