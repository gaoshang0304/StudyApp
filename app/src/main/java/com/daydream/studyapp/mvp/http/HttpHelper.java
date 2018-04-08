package com.daydream.studyapp.mvp.http;

import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface HttpHelper {

    /**
     * 获取首页数据
     */
    Observable<HomeDataBean> getHomeData();

    /**
     * 获取更多首页数据
     */
    Observable<HomeDataBean> getMoreHomeData(String url);

    /**
     * 分类数据
     */
    Flowable<List<CategoryBean>> getCategoryData();

    Flowable<IssueListBean> getCategoryDetail(long id);

    Flowable<IssueListBean> getMoreCategoryDetail(String path);
}
