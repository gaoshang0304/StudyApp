package com.daydream.studyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daydream.studyapp.R;
import com.daydream.studyapp.service.entity.gankio.GankGirlItemBean;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GirlViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;

    public GirlViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.iv_item_image);
    }

    public void bindData(Context context, GankGirlItemBean mBean) {
        Glide.with(context)
                .load(mBean.getUrl())
                .crossFade(500)
                .placeholder(R.drawable.ic_default_meizi)
                .into(imageView);
    }
}
