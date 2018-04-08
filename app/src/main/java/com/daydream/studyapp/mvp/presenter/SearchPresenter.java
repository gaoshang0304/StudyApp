package com.daydream.studyapp.mvp.presenter;

import android.content.Context;

import com.daydream.studyapp.mvp.base.RxPresenter;
import com.daydream.studyapp.mvp.contract.SearchContract;
import com.daydream.studyapp.mvp.manager.DataManager;
import com.daydream.studyapp.mvp.manager.RxUtil;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 搜索页
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-08
 */

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    private Context mContext;

    public SearchPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getHotSearch() {
        addSubscribe(DataManager.getInstance(mContext).getHotSearch()
            .compose(RxUtil.<List<String>>rxOtSchedulerHelper())
            .subscribe(new Consumer<List<String>>() {
                @Override
                public void accept(List<String> list) throws Exception {
                    mView.showHotSearch(list);
                }
            }));
    }
}
