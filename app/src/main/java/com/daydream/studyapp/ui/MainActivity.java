package com.daydream.studyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.daydream.studyapp.R;
import com.daydream.studyapp.ui.fragment.CategoryFragment;
import com.daydream.studyapp.ui.fragment.DiscoverFragment;
import com.daydream.studyapp.ui.fragment.HomeFragment;
import com.daydream.studyapp.ui.fragment.MiddleFragment;
import com.daydream.studyapp.ui.fragment.MineFragment;
import com.daydream.studyapp.util.ToastUtil;
import com.daydream.studyapp.weight.BottomTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements BottomTabView.OnTabItemSelectListener {

    @BindView(R.id.bottom_tab_view)
    BottomTabView bottomTabView;

    private Unbinder bind;
    private Context mContext;
    private long firstTime = 0;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private MiddleFragment mMiddleFragment;
    private DiscoverFragment mDiscoverFragment;
    private MineFragment mMineFragment;
    private FragmentTransaction mTransaction;
    private Fragment mCurrentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        mContext = this;

        initView();
    }

    private void initView() {
        List<BottomTabView.TabItemView> tabList = new ArrayList<>();
        BottomTabView.TabItemView tab_home = new BottomTabView.TabItemView(mContext, "首页",
                R.color.color_bottom_tab_normal, R.color.color_bottom_tab_selected,
                R.drawable.ic_tab_home_normal, R.drawable.ic_tab_home_select);
        BottomTabView.TabItemView tab_category = new BottomTabView.TabItemView(mContext, "分类",
                R.color.color_bottom_tab_normal, R.color.color_bottom_tab_selected,
                R.drawable.ic_tab_category_normal, R.drawable.ic_tab_category_select);
        BottomTabView.TabItemView tab_middle = new BottomTabView.TabItemView(mContext, "论坛",
                R.color.color_bottom_tab_normal, R.color.color_bottom_tab_selected,
                R.drawable.ic_tab_middle_normal, R.drawable.ic_tab_middle_select);
        BottomTabView.TabItemView tab_discover = new BottomTabView.TabItemView(mContext, "发现",
                R.color.color_bottom_tab_normal, R.color.color_bottom_tab_selected,
                R.drawable.ic_tab_discover_normal, R.drawable.ic_tab_discover_select);
        BottomTabView.TabItemView tab_mine = new BottomTabView.TabItemView(mContext, "个人",
                R.color.color_bottom_tab_normal, R.color.color_bottom_tab_selected,
                R.drawable.ic_tab_mine_normal, R.drawable.ic_tab_mine_select);
        tabList.add(tab_home);
        tabList.add(tab_category);
        tabList.add(tab_middle);
        tabList.add(tab_discover);
        tabList.add(tab_mine);
        bottomTabView.setTabItemViews(tabList);
        bottomTabView.setOnTabItemSelectListener(this);
        //fragment
        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mMiddleFragment = new MiddleFragment();
        mDiscoverFragment = new DiscoverFragment();
        mMineFragment = new MineFragment();

        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.add(R.id.fl_container, mHomeFragment).commit();
        mCurrentContent = mHomeFragment;
    }

    @Override
    public void onTabItemSelect(int position) {
        switch (position) {
            case 0:
                switchFragment(mHomeFragment);
                break;
            case 1:
                switchFragment(mCategoryFragment);
                break;
            case 2:
                switchFragment(mMiddleFragment);
                break;
            case 3:
                switchFragment(mDiscoverFragment);
                break;
            case 4:
                switchFragment(mMineFragment);
                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        if (mCurrentContent != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                transaction.hide(mCurrentContent).add(R.id.fl_container, fragment);
            } else {
                transaction.hide(mCurrentContent).show(fragment);
            }
            transaction.commit();
            mCurrentContent = fragment;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 再按一次退出应用
            long secondTime = System.currentTimeMillis();
            if((System.currentTimeMillis() - firstTime) > 1000) {
                ToastUtil.showToast(mContext, "再按一次退出");
                firstTime = secondTime;
            } else {
                try{
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 停止打印Toast
                ToastUtil.cancelToast();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
