package com.daydream.studyapp.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daydream.studyapp.R;
import com.daydream.studyapp.global.MyApplication;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;

import java.util.List;

/**
 * Created by gjc on 16/8/21.
 */

public class GankGirlAdapter extends BaseQuickAdapter<GankListBean, BaseViewHolder>{
    
    private OnItemClickListener onItemClickListener;

    public GankGirlAdapter(@LayoutRes int layoutResId, @Nullable List<GankListBean> data) {
        super(layoutResId, data);
    }

    /**
     * 在StaggeredGridLayoutManager瀑布流中,当需要依据图片实际相对高度,不断动态设置ImageView的LayoutParams时,
     * 会导致快速滑动状态下产生重新排列,重写getItemViewType并设置StaggeredGridLayoutManager.GAP_HANDLING_NONE解决了这个问题，原因目前未知
     * https://github.com/oxoooo/mr-mantou-android/blob/master/app/src/main/java/ooo/oxo/mr/MainAdapter.java
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return Math.round((float) MyApplication.SCREEN_WIDTH / (float) mData.get(position).getHeight() * 10f);
    }

    @Override
    protected void convert(final BaseViewHolder holder, GankListBean item) {
        //存在记录的高度时先Layout再异步加载图片
        if (mData.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.iv_item_image).getLayoutParams();
            layoutParams.height = mData.get(holder.getAdapterPosition()).getHeight();
        }

        Glide.with(mContext).load(item.getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(MyApplication.SCREEN_WIDTH / 2, MyApplication.SCREEN_WIDTH / 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            if (mData.get(holder.getAdapterPosition()).getHeight() <= 0) {
                                int width = resource.getWidth();
                                int height = resource.getHeight();
                                int realHeight = (MyApplication.SCREEN_WIDTH / 2) * height / width;
                                mData.get(holder.getAdapterPosition()).setHeight(realHeight);
                                ViewGroup.LayoutParams lp = holder.getView(R.id.iv_item_image).getLayoutParams();
                                lp.height = realHeight;
                            }
                            holder.setImageBitmap(R.id.iv_item_image, resource);
                        }
                    }
                });
        holder.getView(R.id.cv_img_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    View shareView = view.findViewById(R.id.iv_item_image);
                    onItemClickListener.onItemClickListener(holder.getAdapterPosition(),shareView);
                }
            }
        });
    }

    public void addMoreData(List<GankListBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position,View view);
    }
}
