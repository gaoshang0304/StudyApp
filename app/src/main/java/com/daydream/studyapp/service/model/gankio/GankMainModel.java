package com.daydream.studyapp.service.model.gankio;

import android.support.annotation.NonNull;

import com.daydream.studyapp.service.contract.gankio.GankMainContract;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public class GankMainModel implements GankMainContract.IGankMainModel {

    @NonNull
    public static GankMainModel newInstance() {
        return new GankMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[] {"每日推荐", "干货定制", "福利"};
    }
}
