package com.daydream.studyapp.service.entity.gankio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public class GankGirlListBean {
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankGirlItemBean> getResults() {
        return results;
    }

    public void setResults(List<GankGirlItemBean> results) {
        this.results = results;
    }

    /**
        "_id": "5a2dd04e421aa90fe2f02ccc",
            "createdAt": "2017-12-11T08:24:46.981Z",
            "desc": "12-11",
            "publishedAt": "2017-12-11T08:43:39.682Z",
            "source": "chrome",
            "type": "\u798f\u5229",
            "url": "http://7xi8d6.com1.z0.glb.clouddn.com/20171211082435_CCblJd_Screenshot.jpeg",
            "used": true,
            "who": "daimajia"
    */

    @SerializedName("results")
    private List<GankGirlItemBean> results;

    @Override
    public String toString() {
        return "GankIoCustomListBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
