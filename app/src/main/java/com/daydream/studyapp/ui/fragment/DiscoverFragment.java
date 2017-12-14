package com.daydream.studyapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;

import com.daydream.studyapp.R;
import com.daydream.studyapp.adapter.FragmentAdapter;
import com.daydream.studyapp.service.base.fragment.BaseFragment;
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
    private GankNewsFragment newsFragment;
    private GankGirlFragment girlFragment;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        newsFragment = new GankNewsFragment();
        girlFragment = new GankGirlFragment();

        //tabs
        mTitleList.add("干货");
        mTitleList.add("妹子");

        List<Fragment> list = new ArrayList<>();
        list.add(newsFragment);
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
