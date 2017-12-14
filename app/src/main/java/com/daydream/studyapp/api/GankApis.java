package com.daydream.studyapp.api;

import com.daydream.studyapp.service.entity.gankio.GankGirlListBean;
import com.daydream.studyapp.service.entity.gankio.GankNewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public interface GankApis {

    @GET("api/data/{type}/{count}/{page}")
    Observable<GankNewsListBean> getGankNewsData(@Path("type") String type,
                                                 @Path("count") int count,
                                                 @Path("page") int page);

    //妹子图列表
    @GET("api/data/福利/{count}/{page}")
    Observable<GankGirlListBean> getGankGirlData(@Path("count") int count,
                                             @Path("page") int page);
}

