package com.daydream.studyapp.weight.recyclerview.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daydream.studyapp.weight.recyclerview.BaseViewHolder;
import com.daydream.studyapp.weight.recyclerview.MultiItemSupport;

import java.util.ArrayList;

/**
 * Created by dayDream on 2018/4/5.
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemSupport<T> mMultiItemSupport;

    public MultiItemCommonAdapter(Context context, ArrayList<T> data, MultiItemSupport<T> multiItemSupport) {
        super(context, data, -1);
        mMultiItemSupport = multiItemSupport;
    }

    @Override
    public int getItemViewType(int position)
    {
        return mMultiItemSupport.getItemViewType(position, mData.get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int layoutId = mMultiItemSupport.getLayoutId(viewType);
        BaseViewHolder holder = BaseViewHolder.get(mContext, parent, layoutId);
        return holder;
    }

}
