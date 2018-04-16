package com.daydream.studyapp.ui.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.daydream.studyapp.R;
import com.daydream.studyapp.constants.Constants;
import com.daydream.studyapp.mvp.base.BaseActivity;
import com.daydream.studyapp.util.ShareUtil;
import com.daydream.studyapp.util.SystemUtil;
import com.daydream.studyapp.util.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author gjc
 * @version ;
 * @since 2018-04-12
 */

public class GirlDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_girl_detail)
    ImageView ivGirlDetail;
    private String url;
    private String id;
    private Bitmap bitmap;
    private PhotoViewAttacher mAttacher;
    private static int ACTION_SAVE = 0x00;
    private static int ACTION_SHARE = 0x01;
    private RxPermissions rxPermissions;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolBar, "");
        url = getIntent().getStringExtra(Constants.URL);
        id = getIntent().getStringExtra(Constants.ID);
        if (url != null) {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap = resource;
                    ivGirlDetail.setImageBitmap(resource);
                    mAttacher = new PhotoViewAttacher(ivGirlDetail);
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_girl_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_like:
//                if(isLiked) {
//                    item.setIcon(R.mipmap.ic_toolbar_like_n);
//                    mRealmHelper.deleteLikeBean(this.id);
//                } else {
//                    item.setIcon(R.mipmap.ic_toolbar_like_p);
//                    RealmLikeBean bean = new RealmLikeBean();
//                    bean.setId(this.id);
//                    bean.setImage(url);
//                    bean.setType(Constants.TYPE_GIRL);
//                    bean.setTime(System.currentTimeMillis());
//                    mRealmHelper.insertLikeBean(bean);
//                }
                break;
            case R.id.action_save:
                checkPermissionAndAction(ACTION_SAVE);
                break;
            case R.id.action_share:
                checkPermissionAndAction(ACTION_SHARE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    private void checkPermissionAndAction(final int action) {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            if (action == ACTION_SAVE) {
                                SystemUtil.saveBitmapToFile(mContext, url, bitmap, ivGirlDetail, false);
                            } else if (action == ACTION_SHARE) {
                                ShareUtil.shareImage(mContext, SystemUtil.saveBitmapToFile(mContext, url, bitmap, ivGirlDetail, true), "分享一只妹纸");
                            }
                        } else {
                            ToastUtil.showToast(mContext, "获取写入权限失败");
                        }
                    }
                });
    }
}
