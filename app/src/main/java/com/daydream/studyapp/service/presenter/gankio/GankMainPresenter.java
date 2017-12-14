package com.daydream.studyapp.service.presenter.gankio;

import android.support.annotation.NonNull;

import com.daydream.studyapp.service.contract.gankio.GankMainContract;
import com.daydream.studyapp.service.model.gankio.GankMainModel;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public class GankMainPresenter extends GankMainContract.GankMainPresenter {

    @NonNull
    public static GankMainPresenter newInstance() {
        return new GankMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null) {
            return;
        }
        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public GankMainContract.IGankMainModel getModel() {
        return GankMainModel.newInstance();
    }

    @Override
    public void onStart() {
        //getTabList();
    }
}
