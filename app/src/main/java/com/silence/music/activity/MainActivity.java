package com.silence.music.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.angel.music.R;
import com.sdsmdg.tastytoast.TastyToast;
import com.silence.music.base.BaseActivity;
import com.silence.music.bean.TestBean;
import com.silence.music.contract.TestContract;
import com.silence.music.presenter.TestPresenter;
import com.silence.music.utils.Utils;

/**
 * @author Administrator
 */
public class MainActivity extends BaseActivity<TestPresenter, TestBean> implements TestContract.ITestView {

    private TextView tvLog;
    private long exitTime = 0;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initView();
        mPresenter.attachView(this);
        mPresenter.getTestData(402308L);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected TestPresenter bindPresenter() {
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
            Utils.showTastyToast("再按一次退出程序", TastyToast.DEFAULT);
            exitTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
            //System.exit(0) 貌似Activity的生命周期未走完，所以我注释掉了
            //System.exit(0);
        }
    }
}
