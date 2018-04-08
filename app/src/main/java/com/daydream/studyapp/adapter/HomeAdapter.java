package com.daydream.studyapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.bean.DataBean;
import com.daydream.studyapp.mvp.bean.ItemListBean;
import com.daydream.studyapp.mvp.bean.Tags;
import com.daydream.studyapp.util.FormatUtils;
import com.daydream.studyapp.util.glide.GlideCircleTransform;
import com.daydream.studyapp.util.glide.ImageLoader;
import com.daydream.studyapp.weight.recyclerview.BaseViewHolder;
import com.daydream.studyapp.weight.recyclerview.MultiItemSupport;
import com.daydream.studyapp.weight.recyclerview.adapter.MultiItemCommonAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.daydream.studyapp.ui.fragment.HomeFragment.ITEM_TYPE_BANNER;
import static com.daydream.studyapp.ui.fragment.HomeFragment.ITEM_TYPE_CONTENT;
import static com.daydream.studyapp.ui.fragment.HomeFragment.ITEM_TYPE_TEXT_HEADER;

/**
 * 首页 adapter
 *
 * Created by dayDream on 2018/4/6.
 */

public class HomeAdapter extends MultiItemCommonAdapter<ItemListBean> {

    private int mBannerSize = 0;

    public HomeAdapter(Context context, ArrayList<ItemListBean> data, MultiItemSupport<ItemListBean> multiItemSupport) {
        super(context, data, multiItemSupport);
    }

    public void setBannerSize(int count) {
        mBannerSize = count;
    }

    @Override
    public void convert(BaseViewHolder holder, ItemListBean itemListBean, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM_TYPE_BANNER:
                List<ItemListBean> banner_list = new ArrayList<>();
                List<String> feedList = new ArrayList<>();
                List<String> titleList = new ArrayList<>();
                BGABanner banner = holder.getView(R.id.home_banner);
                banner_list.addAll(mData.subList(0, mData.size()));
                for (ItemListBean item : banner_list) {
                    if (item.getData() != null) {
                        if (item.getData().getCover() != null) {
                            if (item.getData().getCover().getFeed() != null) {
                                feedList.add(item.getData().getCover().getFeed());
                                titleList.add(item.getData().getTitle());
                            }
                        }
                    }
                }
                banner.setAutoPlayAble(mBannerSize > 1);
                banner.setData(feedList, titleList);
                banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                    @Override
                    public void fillBannerItem(BGABanner bgaBanner, ImageView imageView, String feedImgUrl, int position) {
                        ImageLoader.loadImage(mContext, feedImgUrl, imageView);
                    }
                });
                banner.setDelegate(new BGABanner.Delegate() {
                    @Override
                    public void onBannerItemClick(BGABanner bgaBanner, View view, Object o, int i) {

                    }
                });
                break;
            case ITEM_TYPE_TEXT_HEADER:
                holder.setText(R.id.home_tv_header, mData.get(position + mBannerSize - 1).getData().getText());
                break;
            case ITEM_TYPE_CONTENT:
                setVideoItem(holder, mData.get(position + mBannerSize - 1));
                break;

        }
    }

    private void setVideoItem(BaseViewHolder holder, ItemListBean itemListBean) {
        DataBean item = itemListBean.getData();
        int defAvatar = R.drawable.default_avatar;
        String cover = item.getCover().getFeed();
        String avatar = item.getAuthor().getIcon();
        String tagText = "#";

        // 作者出处为空，就显获取提供者的信息
        if (TextUtils.isEmpty(avatar) || avatar == null) {
            avatar = item.getProvider().getIcon();
        }
        //加载封页
        ImageLoader.loadImage(mContext, cover, (ImageView) holder.getView(R.id.iv_cover_feed));
        //如果提供者为空，显示默认
        if (TextUtils.isEmpty(avatar) || avatar == null) {
            Glide.with(mContext)
                    .load(defAvatar)
                    .crossFade()
                    .placeholder(R.drawable.default_avatar)
                    .transform(new GlideCircleTransform(mContext))
                    .into((ImageView) holder.getView(R.id.iv_avatar));
        } else {
            Glide.with(mContext)
                    .load(avatar)
                    .crossFade()
                    .placeholder(R.drawable.default_avatar)
                    .transform(new GlideCircleTransform(mContext))
                    .into((ImageView) holder.getView(R.id.iv_avatar));
        }
        holder.setText(R.id.tv_title, item.getTitle());
        for (Tags tags : item.getTags()) {
            tagText += tags.getName() + "/";
        }
        String timeFormat = FormatUtils.durationFormat(item.getDuration());
        tagText += timeFormat;
        holder.setText(R.id.tv_tag, tagText);
        holder.setText(R.id.tv_category, "#" + item.getCategory());

    }
}
