package com.daydream.studyapp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daydream.studyapp.R;
import com.daydream.studyapp.service.entity.gankio.GankGirlItemBean;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public class GankGirlAdapter extends BaseCompatAdapter<GankGirlItemBean, BaseViewHolder> {


    public GankGirlAdapter(@LayoutRes int layoutResId, @Nullable List<GankGirlItemBean> data) {
        super(layoutResId, data);
    }

    public GankGirlAdapter(@Nullable List<GankGirlItemBean> data) {
        super(data);
    }

    public GankGirlAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankGirlItemBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .crossFade(500)
                .placeholder(R.drawable.ic_default_meizi)
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }

}
