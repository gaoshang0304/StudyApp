package com.daydream.studyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.bean.ItemListBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author gjc
 * @version ;;
 * @since 2018-04-02
 */

public class CategoryDetailAdapter extends RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<ItemListBean> mBean;
    private OnItemClickListener onItemClickListener;

    public CategoryDetailAdapter(Context context, List<ItemListBean> list) {
        mContext = context;
        mBean = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemListBean item = mBean.get(position);
        holder.jcVideo.setUp(item.getData().getPlayUrl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, item.getData().getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final JCVideoPlayerStandard jcVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            jcVideo = (JCVideoPlayerStandard) itemView.findViewById(R.id.jc_video_player);
        }


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(ItemListBean item);
    }
}
