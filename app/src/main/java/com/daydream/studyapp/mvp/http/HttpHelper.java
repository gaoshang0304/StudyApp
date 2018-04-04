package com.daydream.studyapp.mvp.http;

import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public interface HttpHelper {

    Flowable<List<CategoryBean>> getCategoryData();

    Flowable<IssueListBean> getCategoryDetail(long id);

    Flowable<IssueListBean> getMoreCategoryDetail(String path);
}
