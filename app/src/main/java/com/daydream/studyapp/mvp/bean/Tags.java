package com.daydream.studyapp.mvp.bean;

/**
 * Created by dayDream on 2018/4/6.
 */

public class Tags {

    /**
     * id : 737
     * name : 获奖作品
     * actionUrl : eyepetizer://tag/737/?title=%E8%8E%B7%E5%A5%96%E4%BD%9C%E5%93%81
     * adTrack : null
     * desc : null
     * bgPicture : http://img.kaiyanapp.com/ce225210636a1d9ee4804bed55437698.jpeg?imageMogr2/quality/60/format/jpg
     * headerImage : http://img.kaiyanapp.com/ce225210636a1d9ee4804bed55437698.jpeg?imageMogr2/quality/60/format/jpg
     * tagRecType : IMPORTANT
     */

    private int id;
    private String name;
    private String actionUrl;
    private Object adTrack;
    private Object desc;
    private String bgPicture;
    private String headerImage;
    private String tagRecType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getTagRecType() {
        return tagRecType;
    }

    public void setTagRecType(String tagRecType) {
        this.tagRecType = tagRecType;
    }
}
