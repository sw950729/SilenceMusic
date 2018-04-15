package com.silence.music.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Silence
 * @date 2018/3/26.
 */

public abstract class BaseFragmentActivity<P extends BasePresenter> extends RxFragmentActivity  implements IView {


    public P presenter;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectView();
        setContentView(getLayoutId());
        if (presenter != null) {
            presenter = bindPresenter();
            presenter.attchView(this,this);
        }
        initView();
        httpData();
    }

    /**
     * 高亮全屏。巴拉巴拉小魔仙~~~
     */
    protected void injectView() {

    }

    /**
     * 界面跳转
     */
    protected void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 携带数据的页面跳转
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    public abstract void initView();

    public abstract void httpData();

    public abstract int getLayoutId();

    public abstract P bindPresenter();

    @Override
    public void showProgressDialog() {
        showProgressDialog("正在加载，请稍候...");
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(TextUtils.isEmpty(message) ? "正在加载，请稍后..." : message);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public int showEmptyView() {
        return 0;
    }

    @Override
    public int showNetErrorView() {
        return 0;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDataError() {

    }
}
