package com.daydream.studyapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.FragmentAdapter;
import com.daydream.studyapp.mvp.base.BaseFragment;
import com.daydream.studyapp.ui.fragment.gankio.GankGirlFragment;
import com.daydream.studyapp.ui.fragment.gankio.GankNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-12
 */
public class DiscoverFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.tl_tabs)
    TabLayout tabs;
    @BindView(R.id.fab_classify)
    FloatingActionButton fabClassify;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private GankNewsFragment androidFragment, iosFragment, htmlFragment, exSourceFragment, restVideoFragment;
    private GankGirlFragment girlFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        androidFragment = new GankNewsFragment();
        Bundle androidBundle = new Bundle();
        androidBundle.putString("category", "Android");
        androidFragment.setArguments(androidBundle);

        iosFragment = new GankNewsFragment();
        Bundle iosBundle = new Bundle();
        iosBundle.putString("category", "iOS");
        iosFragment.setArguments(iosBundle);

        htmlFragment = new GankNewsFragment();
        Bundle htmlBundle = new Bundle();
        htmlBundle.putString("category", "前端");
        htmlFragment.setArguments(htmlBundle);

        exSourceFragment = new GankNewsFragment();
        Bundle esBundle = new Bundle();
        esBundle.putString("category", "拓展资源");
        exSourceFragment.setArguments(esBundle);

        restVideoFragment = new GankNewsFragment();
        Bundle rvBundle = new Bundle();
        rvBundle.putString("category", "休息视频");
        restVideoFragment.setArguments(rvBundle);

        girlFragment = new GankGirlFragment();
        Bundle girlBundle = new Bundle();
        girlBundle.putString("category", "福利");
        girlFragment.setArguments(girlBundle);

        //tabs
        mTitleList.add("Android");
        mTitleList.add("iOS");
        mTitleList.add("前端");
        mTitleList.add("拓展资源");
        mTitleList.add("休息视频");
        mTitleList.add("福利");

        List<Fragment> list = new ArrayList<>();
        list.add(androidFragment);
        list.add(iosFragment);
        list.add(htmlFragment);
        list.add(exSourceFragment);
        list.add(restVideoFragment);
        list.add(girlFragment);
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(), list, mTitleList));
        vpFragment.setCurrentItem(0);
        vpFragment.addOnPageChangeListener(this);

        tabs.setupWithViewPager(vpFragment);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        fabClassify.setVisibility(View.GONE);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            fabClassify.setVisibility(View.VISIBLE);
        } else {
            fabClassify.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
