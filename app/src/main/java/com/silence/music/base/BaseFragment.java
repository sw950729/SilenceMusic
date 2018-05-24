package com.silence.music.base;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.angel.music.R;
import com.silence.music.view.loading.LoadingViewHelper;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * @author Silence
 * @date 2018/5/16
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements IView {

    public P presenter;
    public ProgressDialog progressDialog;
    public View mView;
    public Context mContext;
    public LoadingViewHelper mLoadingViewHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mContext = getContext();
        if (presenter == null) {
            presenter = bindPresenter();
            if (presenter != null) {
                presenter.attchView(this, this);
            }
        }
        initView();
        return mView;
    }

    public abstract void initView();

    public abstract void httpData();

    protected abstract int getLayoutId();

    protected abstract P bindPresenter();

    @SuppressLint("InflateParams")
    public void initLoadingLayout(Context mContext, int layoutId) {
        if (mContext == null || mView.findViewById(layoutId) == null) {
            return;
        }
        mLoadingViewHelper = new LoadingViewHelper.Builder()
                .setDataView(mView.findViewById(layoutId))
                .setLoadingView(getLayoutInflater().inflate(R.layout.layout_loading, null))
                .setErrorView(getLayoutInflater().inflate(R.layout.layout_error, null))
                .setEmptyView(getLayoutInflater().inflate(R.layout.layout_empty, null))
                .setRefreshListener(v -> loadingClick())
                .build();
        mLoadingViewHelper.showLoadingView();
    }

    public void initLoadingLayout(int layoutId) {
        this.initLoadingLayout(getActivity(), layoutId);
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog("正在加载，请稍候...");
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(TextUtils.isEmpty(message) ? "正在加载，请稍后..." : message);
        progressDialog.show();
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
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 界面跳转
     */
    protected void startActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    /**
     * 携带数据的页面跳转
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            httpData();
        }
    }
}
