package com.daydream.studyapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.bean.gank.GankListBean;
import com.daydream.studyapp.util.FormatUtils;
import com.daydream.studyapp.util.glide.ImageLoader;
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
public class GankListAdapter extends CommonAdapter<GankListBean> {

    private OnItemClickListener mListener;

    public GankListAdapter(Context context, List<GankListBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    public void addMoreData(List<GankListBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void convert(BaseViewHolder holder, final GankListBean item, int position) {
        //picture
        if (item.getImages() == null) {
            holder.setVisible(R.id.iv_icon, false);
        } else {
            holder.setVisible(R.id.iv_icon, true);
            ImageLoader.loadImage(mContext, item.getImages().get(0), (ImageView) holder.getView(R.id.iv_icon));
        }
        //title
        holder.setText(R.id.tv_tech_title, item.getDesc());
        //author
        holder.setText(R.id.tv_tech_author, item.getWho() == null ? "无名" : item.getWho());
        //publish time
        holder.setText(R.id.tv_tech_time, FormatUtils.subStandardTime(item.getPublishedAt()));

        holder.getView(R.id.cv_tech_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(item);
                }
            }
        });
//        holder.setOnItemClickListener(R.id.cv_tech_content, new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public interface OnItemClickListener {
        void onItemClickListener(GankListBean item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
