package com.silence.music.login;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.angel.music.R;
import com.silence.music.base.BaseActivity;
import com.silence.music.bean.LoginBean;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.ILoginView {


    private TextView tv_log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void injectView() {

    }

    @Override
    public void initView() {
        tv_log= (TextView) findViewById(R.id.tv_log);
    }

    @Override
    public void httpData() {
        presenter.login();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showDataSuccess(LoginBean loginBean) {
        tv_log.setText(loginBean.imgUrl);
        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDataError() {
        Toast.makeText(LoginActivity.this, "failure", Toast.LENGTH_LONG).show();
    }
}
