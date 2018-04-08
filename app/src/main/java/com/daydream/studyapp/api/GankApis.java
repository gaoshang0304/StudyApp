package com.daydream.studyapp.api;

import com.daydream.studyapp.mvp.bean.gank.ResponseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 干活集中营
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-08
 */

public interface GankApis {

    /**
     * 分类数据
     */
    @GET("data/{category}/20/{page}")
    Observable<ResponseResult> getGankCategoryData(@Path("category") String category, @Path("page") int page);

}
