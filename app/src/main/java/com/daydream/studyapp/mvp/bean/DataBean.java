package com.daydream.studyapp.mvp.bean;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class DataBean {
    /**
     * dataType : Banner
     * id : 0
     * title :
     * description :
     * image : http://img.kaiyanapp.com/eef24aa10ab6cf17b5a512943ec22053.jpeg?imageMogr2/quality/60/format/jpg
     * actionUrl :
     * adTrack : null
     * shade : false
     * label : null
     * labelList : null
     * header : null
     */

    private String dataType;
    private long id;
    public Long duration;
    private long date;
    private String title;
    private Cover cover;
    private Author author;
    private Provider provider;
    private List<Tags> tags;
    private String description;
    private String text;
    private String image;
    private String actionUrl;
    private String playUrl;
    private String category;
    private Object adTrack;
    private boolean shade;
    private Object label;
    private Object labelList;
    private Object header;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String url) {
        this.playUrl = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Author getAuthor() {
        return author;
    }

    public void setProvider(Author author) {
        this.author = author;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> list) {
        this.tags = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getLabelList() {
        return labelList;
    }

    public void setLabelList(Object labelList) {
        this.labelList = labelList;
    }

    public Object getHeader() {
        return header;
    }

    public void setHeader(Object header) {
        this.header = header;
    }
}
