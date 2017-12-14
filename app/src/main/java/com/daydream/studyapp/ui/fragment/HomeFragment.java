package com.daydream.studyapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.service.base.fragment.BaseFragment;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class HomeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
}
