package com.daydream.studyapp.api;

import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 开眼视频
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface OpenEyesApis {

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
    @GET()
    Flowable<IssueListBean> getMoreCategoryDetail(String path);

}
