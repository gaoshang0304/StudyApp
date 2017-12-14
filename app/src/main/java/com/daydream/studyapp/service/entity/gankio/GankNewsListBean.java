package com.daydream.studyapp.service.entity.gankio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankNewsListBean {
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankNewsItemBean> getResults() {
        return results;
    }

    public void setResults(List<GankNewsItemBean> results) {
        this.results = results;
    }

    /**
     * _id : 5832662b421aa929b0f34e99
     * createdAt : 2016-11-21T11:12:43.567Z
     * desc :  深入Android渲染机制
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : web
     * type : Android
     * url : http://blog.csdn.net/ccj659/article/details/53219288
     * used : true
     * who : Chauncey
     */

    private List<GankNewsItemBean> results;

    @Override
    public String toString() {
        return "GankIoCustomListBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
