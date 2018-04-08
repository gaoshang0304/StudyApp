package com.daydream.studyapp.api;

import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.bean.HomeDataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 开眼视频
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface OpenEyesApis {

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    Observable<HomeDataBean> getHomeData();

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    Observable<HomeDataBean> getMoreHomeData(@Url String url);

    /**
     * 分类
     */
    @GET("v4/categories")
    Flowable<List<CategoryBean>> getCategoryList();

    /**
     * 分类详情
     */
    @GET("v4/categories/videoList?")
    Flowable<IssueListBean> getCategoryDetail(@Query("id") long id);

    /**
     * 分类详情
     */
    @GET
    Flowable<IssueListBean> getMoreCategoryDetail(@Url String path);

}
