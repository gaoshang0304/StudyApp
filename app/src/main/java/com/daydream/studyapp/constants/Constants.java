package com.daydream.studyapp.constants;

import android.os.Environment;

import java.io.File;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public class Constants {

    /**
     * 干活集中营
     */
    public static String GANK_HOST = "http://gank.io/api/";

    /**
     * 开眼视频
     */
    public static String OPEN_EYE_HOST = "http://baobab.kaiyanapp.com/api/";

    /**
     * 分类详情item
     */
    public static final String CATEGORY_DETAIL_ITEM = "category_detail_item";

    public static final String URL = "url";

    public static final String ID = "id";

    /**
     * 保存路径
     */
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "daydream" + File.separator + "study";
}
