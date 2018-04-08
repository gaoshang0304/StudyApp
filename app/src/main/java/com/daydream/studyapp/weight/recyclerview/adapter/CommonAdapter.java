package com.daydream.studyapp.weight.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.daydream.studyapp.weight.recyclerview.BaseViewHolder;

import java.util.List;


/**
 * Recycler View 通用adapter
 *
 * Created by dayDream on 2018/4/5.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int mLayoutId;
    protected List<T> mData;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
       mContext = context;
       mInflater = LayoutInflater.from(context);
       mData = data;
       mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = BaseViewHolder.get(mContext, parent, mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public abstract void convert(BaseViewHolder holder, T t, int position);
}
