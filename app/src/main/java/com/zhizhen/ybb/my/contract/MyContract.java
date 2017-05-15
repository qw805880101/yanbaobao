package com.zhizhen.ybb.my.contract;

import com.psylife.wrmvplibrary.base.WRBaseModel;
import com.psylife.wrmvplibrary.base.WRBaseView;
import com.zhizhen.ybb.base.YbBasePresenter;
import com.zhizhen.ybb.bean.LoginBean;

import rx.Observable;

/**
 * 作者：tc on 2017/5/15.
 * 邮箱：qw805880101@qq.com
 * 版本：v1.0
 */
public interface MyContract {

    //修改个人信息
    public abstract class GetPersonInfoPresenter extends YbBasePresenter<GetPersonInfoModel, GetPersonInfoView> {
        public abstract void getPersonInfo(String token);

    }
    interface GetPersonInfoModel extends WRBaseModel {
        Observable<LoginBean> getPersonInfo(String token);
    }
    interface GetPersonInfoView extends WRBaseView {
        void showPersonInfo(LoginBean mPersonInfo);
    }


}
