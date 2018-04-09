package com.daydream.studyapp.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;
import com.daydream.studyapp.weight.recyclerview.BaseViewHolder;
import com.daydream.studyapp.weight.recyclerview.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjc
 * @version ;
 * @since 2018-04-09
 */

public class GankGirlAdapter extends CommonAdapter<GankListBean> {

    public GankGirlAdapter(Context context, List<GankListBean> bean, int layoutId) {
        super(context, bean, layoutId);
    }

    public void addMoreData(List<GankListBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void convert(final BaseViewHolder holder, GankListBean item, int position) {
        // 随机高度, 模拟瀑布效果.
        List<Integer> mHeights = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            mHeights.add((int) (200 + Math.random() * 300));
        }
        ViewGroup.LayoutParams params = holder.getView(R.id.iv_item_image).getLayoutParams();
        params.height = mHeights.get(position);
        holder.getView(R.id.iv_item_image).setLayoutParams(params);

        Glide.with(mContext)
             .load(item.getUrl())
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into((ImageView) holder.getView(R.id.iv_item_image));
    }
}
