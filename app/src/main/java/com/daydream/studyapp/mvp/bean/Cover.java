package com.daydream.studyapp.mvp.bean;

/**
 * Created by dayDream on 2018/4/6.
 */

public class Cover {

    /**
     * feed : http://img.kaiyanapp.com/5ef9ca7688d6c5db48e603af9ec97bc5.jpeg?imageMogr2/quality/60/format/jpg
     * detail : http://img.kaiyanapp.com/5ef9ca7688d6c5db48e603af9ec97bc5.jpeg?imageMogr2/quality/60/format/jpg
     * blurred : http://img.kaiyanapp.com/4f2b2ddefdb25ae5bc55ebca17f7d949.jpeg?imageMogr2/quality/60/format/jpg
     * sharing : null
     * homepage : http://img.kaiyanapp.com/5ef9ca7688d6c5db48e603af9ec97bc5.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
     */

    private String feed;
    private String detail;
    private String blurred;
    private Object sharing;
    private String homepage;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public Object getSharing() {
        return sharing;
    }

    public void setSharing(Object sharing) {
        this.sharing = sharing;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
