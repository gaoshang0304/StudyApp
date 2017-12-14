package com.daydream.studyapp.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.GankGirlAdapter;
import com.daydream.studyapp.adapter.GankGirlsAdapter;
import com.daydream.studyapp.api.GankApis;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.service.base.fragment.BaseFragment;
import com.daydream.studyapp.service.entity.gankio.GankGirlItemBean;
import com.daydream.studyapp.service.entity.gankio.GankGirlListBean;
import com.daydream.studyapp.service.http.RetrofitHelper;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankGirlFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_gankio_girl)
    RecyclerView rvGankioGirl;
    private int mPage = 1;
    private GankGirlsAdapter mAdapter;
    private List<GankGirlItemBean> mBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_girl;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        loadData();
    }

    private void handleData(List<GankGirlItemBean> item) {
        mAdapter = new GankGirlsAdapter(getActivity(), item);
//        mAdapter.setOnLoadMoreListener(this, rvGankioGirl);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
        rvGankioGirl.setAdapter(mAdapter);
        rvGankioGirl.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    /**
     * 请求数据
     */
    private void loadData() {
        GankApis api = RetrofitHelper.createApi(GankApis.class, Constants.GANK_HOST);
        api.getGankGirlData(20, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankGirlListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankGirlListBean value) {
                        handleData(value.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
