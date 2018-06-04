package com.moudle.view.loading;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.basemoudle.R;


/**
 * 功能：帮助切换错误,正在加载的页面
 */
public class LoadingViewHelper {
    /**
     * 切换不同视图的帮助类
     */
    public OverlapViewHelper mViewHelper;
    /**
     * 错误页面
     */
    public View mErrorView;
    /**
     * 正在加载页面
     */
    public View mLoadingView;
    /**
     * 空页面
     */
    public View mEmptyView;
    /**
     * 正在加载页面的进度
     */
    public ImageView img_progress;

    private AnimationDrawable mAnimationDrawable;

    public LoadingViewHelper(View view) {
        this(new OverlapViewHelper(view));
    }

    public LoadingViewHelper(OverlapViewHelper helper) {
        this.mViewHelper = helper;
    }


    public void setUpErrorView(View view, View.OnClickListener listener) {
        mErrorView = view;
        mErrorView.setClickable(true);
        View rl = view.findViewById(R.id.ll_error_refresh);
        if (rl != null) {
            rl.setOnClickListener(listener);
        }
    }

    public void setUpLoadingView(View view) {
        mLoadingView = view;
        mLoadingView.setClickable(true);
        img_progress = view.findViewById(R.id.img_progress);
        mAnimationDrawable = (AnimationDrawable) img_progress.getDrawable();
        // 默认进入页面就开启动画
        startProgress();
    }

    public void showErrorView() {
        mViewHelper.showCaseLayout(mErrorView);
        stopProgress();
    }

    public void showLoadingView() {
        mViewHelper.showCaseLayout(mLoadingView);
    }

    public void showDataView() {
        mViewHelper.restoreLayout();
        stopProgress();
    }

    public void setEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
    }

    public void showEmptyView() {
        mViewHelper.showCaseLayout(mEmptyView);
    }

    public void stopProgress() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    public void startProgress() {
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    public void releaseVaryView() {
        mErrorView = null;
        mLoadingView = null;
    }

    public static class Builder {
        private View mErrorView;
        private View mLoadingView;
        private View mDataView;
        private View mEmptyView;
        private View.OnClickListener mRefreshListener;

        public Builder setErrorView(View errorView) {
            mErrorView = errorView;
            return this;
        }

        public Builder setEmptyView(View mEmptyView) {
            this.mEmptyView = mEmptyView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setDataView(View dataView) {
            mDataView = dataView;
            return this;
        }

        public Builder setRefreshListener(View.OnClickListener refreshListener) {
            mRefreshListener = refreshListener;
            return this;
        }

        public LoadingViewHelper build() {
            LoadingViewHelper helper = new LoadingViewHelper(mDataView);
            if (mErrorView != null) {
                helper.setUpErrorView(mErrorView, mRefreshListener);
            }
            if (mLoadingView != null) {
                helper.setUpLoadingView(mLoadingView);
            }
            if (mEmptyView != null) {
                helper.setEmptyView(mEmptyView);
            }
            return helper;
        }
    }

}
