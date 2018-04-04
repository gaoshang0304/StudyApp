package com.daydream.studyapp.mvp.manager;

import android.content.Context;

import com.daydream.studyapp.api.OpenEyesApis;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.mvp.bean.IssueListBean;
import com.daydream.studyapp.mvp.http.HttpHelper;
import com.daydream.studyapp.mvp.http.RetrofitHelper;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class DataManager implements HttpHelper {

    private volatile static DataManager instance;
    private final OpenEyesApis retrofitService;

    private DataManager(Context context) {
        retrofitService = RetrofitHelper.getInstance(context).getOpenEyesService();
    }

    //由于该对象会被频繁调用，采用单例模式，下面是一种线程安全模式的单例写法
    public static DataManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager(context);
                }
            }
        }
        return instance;
    }

    @Override
    public Flowable<List<CategoryBean>> getCategoryData() {
        return retrofitService.getCategoryList();
    }

    @Override
    public Flowable<IssueListBean> getCategoryDetail(long id) {
        return retrofitService.getCategoryDetail(id);
    }

    @Override
    public Flowable<IssueListBean> getMoreCategoryDetail(String path) {
        return retrofitService.getMoreCategoryDetail(path);
    }
}
