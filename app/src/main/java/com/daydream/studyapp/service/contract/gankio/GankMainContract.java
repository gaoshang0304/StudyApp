package com.daydream.studyapp.service.contract.gankio;

import com.daydream.studyapp.service.base.BasePresenter;
import com.daydream.studyapp.service.base.IBaseFragment;
import com.daydream.studyapp.service.base.IBaseModel;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-13
 */

public interface GankMainContract {
    //主页接口

    abstract class GankMainPresenter extends BasePresenter<IGankMainModel, IGankMainView> {
        public abstract void getTabList();
    }

    interface IGankMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IGankMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }

}
