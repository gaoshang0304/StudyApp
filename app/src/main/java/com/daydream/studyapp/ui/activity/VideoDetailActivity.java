package com.daydream.studyapp.ui.activity;

import android.os.Bundle;

import com.daydream.studyapp.mvp.base.BaseMvpActivity;
import com.daydream.studyapp.mvp.base.BasePresenter;

/**
 * Created by dayDream on 2018/4/6.
 */

public class VideoDetailActivity extends BaseMvpActivity {
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
