package com.silence.music.login;

import android.os.Bundle;
import android.widget.Toast;

import com.angel.music.R;
import com.silence.music.base.BaseActivity;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.ILoginView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void injectView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void httpData() {
        presenter.login("angel", "123");
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
    public void showDataSuccess() {
        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDataError() {

    }
}
