package com.daydream.studyapp.mvp.bean;

import java.util.List;

/**
 * 首页数据
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class HomeDataBean {

    private String nextPageUrl;
    private long nextPublishTime;
    private String newestIssueType;
    private Object dialog;
    private List<IssueListBean> issueList;

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public String getNewestIssueType() {
        return newestIssueType;
    }

    public void setNewestIssueType(String newestIssueType) {
        this.newestIssueType = newestIssueType;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public List<IssueListBean> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueListBean> issueList) {
        this.issueList = issueList;
    }

}
