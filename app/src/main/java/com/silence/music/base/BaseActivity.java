package com.silence.music.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.angel.music.R;
import com.silence.music.view.loading.LoadingViewHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * @author Silence
 * @date 2018/3/20.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView {

    public P presenter;
    public ProgressDialog progressDialog;
    public LoadingViewHelper mLoadingViewHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectView();
        setContentView(getLayoutId());
        if (presenter == null) {
            presenter = bindPresenter();
            presenter.attchView(this, this);
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

    @SuppressLint("InflateParams")
    public void initLoadingLayout(Context mContext, int layoutId) {
        if (mContext == null || findViewById(layoutId) == null) {
            return;
        }
        mLoadingViewHelper = new LoadingViewHelper.Builder()
                .setDataView(findViewById(layoutId))
                .setLoadingView(getLayoutInflater().inflate(R.layout.layout_loading, null))
                .setErrorView(getLayoutInflater().inflate(R.layout.layout_error, null))
                .setEmptyView(getLayoutInflater().inflate(R.layout.layout_empty, null))
                .setRefreshListener(v -> loadingClick())
                .build();
        mLoadingViewHelper.showLoadingView();
    }

    public void initLoadingLayout(int layoutId) {
        this.initLoadingLayout(this, layoutId);
    }

    public void loadingClick() {
        mLoadingViewHelper.showLoadingView();
        bindPresenter();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showEmptyView() {
    }

    @Override
    public void showNetErrorView() {
    }

    @Override
    public void showDataError() {

    }

    @Override
    public void showDataSuccess() {
        if (null != mLoadingViewHelper) {
            mLoadingViewHelper.showDataView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
