package com.daydream.studyapp.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.daydream.studyapp.R
import com.daydream.studyapp.mvp.bean.ItemListBean
import com.daydream.studyapp.ui.activity.VideoDetailActivity
import com.daydream.studyapp.util.FormatUtils.durationFormat
import com.daydream.studyapp.util.JsonUtil
import com.daydream.studyapp.util.glide.GlideCircleTransform
import io.reactivex.Observable

/**
 * Created by dayDream on 2018/4/6.
 * desc: 首页精选的 Adapter
 */

class HomeAdapter(context: Context, data: ArrayList<ItemListBean>)
    : CommonAdapter<ItemListBean>(context, data, -1) {


    // banner 作为 RecyclerView 的第一项
    var bannerItemSize = 0

    companion object {

        private val ITEM_TYPE_BANNER = 1    //Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private val ITEM_TYPE_CONTENT = 3    //item
    }

    /**
     * 设置 Banner 大小
     */
    fun setBannerSize(count: Int) {
        bannerItemSize = count
    }

    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<ItemListBean>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }


    /**
     * 得到 Item 的类型
     */
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 ->
                ITEM_TYPE_BANNER
            mData[position + bannerItemSize - 1].type == "textHeader" ->
                ITEM_TYPE_TEXT_HEADER
            else ->
                ITEM_TYPE_CONTENT
        }
    }


    /**
     *  得到 RecyclerView Item 数量（Banner 作为一个 item）
     */
    override fun getItemCount(): Int {
        return when {
            mData.size > bannerItemSize -> mData.size - bannerItemSize + 1
            mData.isEmpty() -> 0
            else -> 1
        }
    }


    /**
     * 绑定布局
     */
    override fun bindData(holder: ViewHolder, data: ItemListBean, position: Int) {
        when (getItemViewType(position)) {
        //Banner
            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<ItemListBean> = mData.take(bannerItemSize).toCollection(ArrayList())
                val bannerFeedList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()
                //取出banner 显示的 img 和 Title
                Observable.fromIterable(bannerItemData)
                        .subscribe({ list ->
                            bannerFeedList.add(list.data?.cover?.feed?:"")
                            bannerTitleList.add(list.data?.title?:"")
                        })

                //设置 banner
                with(holder) {
                    getView<BGABanner>(R.id.home_banner).run {
                        setAutoPlayAble(bannerFeedList.size > 1)
                        setData(bannerFeedList, bannerTitleList)
                        setAdapter(object : BGABanner.Adapter<ImageView, String> {
                            override fun fillBannerItem(bgaBanner: BGABanner?, imageView: ImageView?, feedImageUrl: String?, position: Int) {
                                Glide.with(mContext)
                                        .load(feedImageUrl)
                                        .crossFade()
                                        .placeholder(R.drawable.placeholder_banner)
                                        .into(imageView)

                            }
                        })
                    }
                }
                //没有使用到的参数在 kotlin 中用"_"代替
                holder.getView<BGABanner>(R.id.home_banner).setDelegate { _, imageView, _, i ->

                    goToVideoPlayer(mContext as Activity, imageView, bannerItemData[i])

                }
            }
        //TextHeader
            ITEM_TYPE_TEXT_HEADER -> {
                holder.setText(R.id.home_tv_header, mData[position + bannerItemSize - 1].data?.text?:"")
            }

        //content
            ITEM_TYPE_CONTENT -> {
                setVideoItem(holder, mData[position + bannerItemSize - 1])
            }


        }

    }

    /**
     *  创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER ->
                ViewHolder(inflaterView(R.layout.item_home_banner, parent))
            ITEM_TYPE_TEXT_HEADER ->
                ViewHolder(inflaterView(R.layout.item_home_text_header, parent))
            else ->
                ViewHolder(inflaterView(R.layout.item_home_content, parent))
        }
    }


    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view!!
    }


    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: ViewHolder, item: ItemListBean) {
        val itemData = item.data

        val defAvatar = R.drawable.default_avatar
        val cover = itemData?.cover?.feed
        var avatar = itemData?.author?.icon
        var tagText: String? = "#"

        // 作者出处为空，就显获取提供者的信息
        if (avatar.isNullOrEmpty()) {
            avatar = itemData?.provider?.icon
        }
        // 加载封页图
        Glide.with(mContext)
                .load(cover)
                .placeholder(R.drawable.placeholder_banner)
                .crossFade()
                .into(holder.getView(R.id.iv_cover_feed))

        // 如果提供者信息为空，就显示默认
        if (avatar.isNullOrEmpty()) {
            Glide.with(mContext)
                    .load(defAvatar)
                    .placeholder(R.drawable.default_avatar)
                    .crossFade()
                    .transform(GlideCircleTransform(mContext))
                    .into(holder.getView(R.id.iv_avatar))

        } else {
            Glide.with(mContext)
                    .load(avatar)
                    .placeholder(R.drawable.default_avatar)
                    .crossFade()
                    .transform(GlideCircleTransform(mContext))
                    .into(holder.getView(R.id.iv_avatar))
        }
        holder.setText(R.id.tv_title, itemData?.title?:"")

        //遍历标签
        itemData?.tags?.take(4)?.forEach {
            tagText += (it.name + "/")
        }
        // 格式化时间
        val timeFormat = durationFormat(itemData.duration?:0)

        tagText += timeFormat

        holder.setText(R.id.tv_tag, tagText!!)

        holder.setText(R.id.tv_category, "#" + itemData?.category)

        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), item)
        })


    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: ItemListBean) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, JsonUtil.parseBeanToString(itemData))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, "IMG_TRANSITION")
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_finish_zoom_in, R.anim.activity_finish_zoom_out)
        }
    }


}
