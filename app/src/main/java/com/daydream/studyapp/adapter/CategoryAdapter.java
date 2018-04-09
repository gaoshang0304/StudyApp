package com.daydream.studyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.bean.CategoryBean;
import com.daydream.studyapp.util.glide.ImageLoader;

import java.util.List;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<CategoryBean> mBean;
    private OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context, List<CategoryBean> list) {
        mContext = context;
        mBean = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CategoryBean item = mBean.get(position);
        ImageLoader.loadImage(mContext, item.getBgPicture(), holder.ivItemBg);
        holder.tvCategoryName.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(item, holder.ivItemBg);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivItemBg;
        private final TextView tvCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivItemBg = (ImageView) itemView.findViewById(R.id.iv_item_bg);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
        }


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(CategoryBean item, View itemView);
    }
}
