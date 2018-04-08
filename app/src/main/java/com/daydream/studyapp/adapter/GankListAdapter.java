package com.daydream.studyapp.adapter;

import android.content.Context;

import com.daydream.studyapp.mvp.bean.gank.GankListBean;
import com.daydream.studyapp.weight.recyclerview.BaseViewHolder;
import com.daydream.studyapp.weight.recyclerview.adapter.CommonAdapter;

import java.util.List;

/**
 * 干活集中营 数据列表
 *
 * @author gjc
 * @version ;
 * @since 2018-04-08
 */

public class GankListAdapter extends CommonAdapter<GankListBean>{

    public GankListAdapter(Context context, List<GankListBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    public void addMoreData(List<GankListBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void convert(BaseViewHolder holder, GankListBean gankListBean, int position) {

    }
}
