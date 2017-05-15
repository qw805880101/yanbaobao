package com.zhizhen.ybb.my;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.psylife.wrmvplibrary.utils.LogUtil;
import com.zhizhen.ybb.R;
import com.zhizhen.ybb.base.YbBaseActivity;
import com.zhizhen.ybb.bean.LoginBean;
import com.zhizhen.ybb.my.contract.MyContract.GetPersonInfoView;
import com.zhizhen.ybb.my.model.MyModel;
import com.zhizhen.ybb.my.presenter.MyPresenter;

import butterknife.BindView;

/**
 * 作者：tc on 2017/5/15.
 * 邮箱：qw805880101@qq.com
 * 版本：v1.0
 */
public class MyActivity extends YbBaseActivity<MyPresenter, MyModel> implements GetPersonInfoView, OnClickListener {

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.image_sex)
    ImageView imageSex;

    @BindView(R.id.txt_age)
    TextView txtAge;

    @BindView(R.id.rl_vison)
    RelativeLayout rlVison;

    @BindView(R.id.rl_device)
    RelativeLayout rlDevice;

    @BindView(R.id.rl_follow)
    RelativeLayout rlFollow;

    @BindView(R.id.bt_exit)
    RelativeLayout btExit;


    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {
        this.startProgressDialog(this);
        mPresenter.getPersonInfo("s71h2krjydnlf");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(Throwable e) {
        this.stopProgressDialog();
        Toast.makeText(this, "网络错误，请稍后再试", Toast.LENGTH_LONG).show();
        LogUtil.d("e=>>>" + e);
    }

    @Override
    public void showPersonInfo(LoginBean mPersonInfo) {
        this.stopProgressDialog();
        System.out.println(mPersonInfo.toString());
    }
}
