package com.silence.music.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence.music.base.ibase.IBasePresenter;
import com.silence.music.base.ibase.IView;


/**
 * Created by tinlone on 2017/5/26 0026.
 */

public abstract class BaseFragment<T extends IBasePresenter, V> extends Fragment implements IView<V> {
    protected View mRootView;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(enrollLayoutId(), container, false);
            if (null != enrollPresenter()) {
                mPresenter = enrollPresenter();
            }
            initView(mRootView);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    /**
     * Base基本类
     */
    protected abstract int enrollLayoutId();

    /**
     * 获取Presenter
     */
    protected T enrollPresenter() {
        return null;
    }

    /**
     * 界面跳转
     *
     * @param clazz
     */
    protected void startActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 将空视图或者无网络的视图显示在哪个布局上
     *
     * @return
     */
    protected View enrollLoadingView() {
        return null;
    }

    /**
     * 显示加载弹框
     */
    @Override
    public void loading() {

    }

    /**
     * 隐藏加载弹框
     */
    @Override
    public void hiding() {

    }

    /**
     * 加载数据失败
     *
     * @param errorMessage
     * @param tag
     */
    @Override
    public void showDataError(String errorMessage, int tag) {
        hiding();
    }

    /**
     * 加载数据成功
     *
     * @param datas
     */
    @Override
    public void showDataSuccess(V datas) {
        hiding();
    }

    /**
     * 加载失败的View，显示无网络的视图
     */
    @Override
    public void showNetErrorView() {
        hiding();
    }

    /**
     * 加载不到数据的View
     */
    @Override
    public void showEmptyView(String msg, int img) {

    }

    protected abstract void initData();

    protected abstract void initView(View RootView);

}
