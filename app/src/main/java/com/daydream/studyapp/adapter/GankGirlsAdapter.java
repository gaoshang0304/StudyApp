package com.daydream.studyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daydream.studyapp.R;
import com.daydream.studyapp.service.entity.gankio.GankGirlItemBean;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */
public class GankGirlsAdapter extends RecyclerView.Adapter<GirlViewHolder> {

    private Context mContext;
    private List<GankGirlItemBean> mBean;

    public GankGirlsAdapter(Context context, List<GankGirlItemBean> item) {
        super();
        this.mContext = context;
        this.mBean = item;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_gank_girl, parent, false);
        GirlViewHolder holder = new GirlViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        GankGirlItemBean bean = mBean.get(position);
        holder.bindData(mContext, bean);
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

}
