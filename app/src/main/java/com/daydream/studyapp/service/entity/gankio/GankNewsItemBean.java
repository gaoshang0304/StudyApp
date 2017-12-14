package com.daydream.studyapp.service.entity.gankio;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankNewsItemBean {

    /**
     * _id : 5a24b074421aa90fe725368a
     * createdAt : 2017-12-04T10:18:28.419Z
     * desc : 无需root，不用刷机也能使用xposed！
     * images : ["http://img.gank.io/de2b867c-ca59-46f8-a5f9-e15c5303903f"]
     * publishedAt : 2017-12-05T08:48:31.384Z
     * source : web
     * type : Android
     * url : http://weishu.me/2017/12/02/non-root-xposed/
     * used : true
     * who : weishu
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
