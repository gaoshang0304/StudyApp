package com.daydream.studyapp.weight.recyclerview;

/**
 * 多布局接口
 *
 * Created by dayDream on 2018/4/5.
 */

public interface MultiItemSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
