package com.daydream.studyapp.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.daydream.studyapp.R;
import com.daydream.studyapp.mvp.base.BaseFragment;
import com.daydream.studyapp.weight.WaterMarkBg;

import butterknife.BindView;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class MiddleFragment extends BaseFragment {

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_middle;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        Drawable drawable = new WaterMarkBg("测试一下啦", getResources().getColor(R.color.backgroundColor),
                getResources().getColor(R.color.text_color_light), 35);
        ll_parent.setBackground(drawable);
    }
}
