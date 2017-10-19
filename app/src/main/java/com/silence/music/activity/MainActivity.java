package com.silence.music.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.angel.music.R;
import com.silence.music.base.BaseActivity;
import com.silence.music.bean.TestBean;
import com.silence.music.contract.TestContract;
import com.silence.music.presenter.TestPresenter;

/**
 * @author Administrator
 */
public class MainActivity extends BaseActivity<TestPresenter,TestBean> implements TestContract.ITestView {

    private TextView tvLog;
    private long exitTime = 0;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initView();
        mPresenter.attachView(this);
        mPresenter.getTestData(402308L);
    }

    @Override
    protected int enrollLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected TestPresenter enrollPresenter() {
        return new TestPresenter();
    }

    @Override
    public void showDataSuccess(TestBean data) {
        tvLog.setText(data.toString());

    }

    private void initView() {
        tvLog = (TextView) findViewById(R.id.tv_log);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "双击关闭应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
            System.exit(0);
        }
    }
}
