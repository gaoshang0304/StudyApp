package com.daydream.studyapp.mvp.manager;

import com.daydream.studyapp.api.OpenEyesApis;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.mvp.bean.CategoryBean;
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

    private final OpenEyesApis retrofitService;

    public DataManager() {
        retrofitService = RetrofitHelper.getInstance().createRetrofit(OpenEyesApis.class, Constants.OPEN_EYE_HOST);
    }

    @Override
    public Flowable<List<CategoryBean>> getCategoryData() {
        return retrofitService.getCategoryList();
    }
}
