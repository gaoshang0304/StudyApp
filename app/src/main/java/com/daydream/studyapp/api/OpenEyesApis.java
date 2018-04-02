package com.daydream.studyapp.api;

import com.daydream.studyapp.mvp.bean.CategoryBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

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
}
