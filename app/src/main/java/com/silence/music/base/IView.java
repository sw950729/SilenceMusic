package com.silence.music.base;


/**
 * Created by Silence on 2018/3/19.
 */

public interface IView {

    /**
     * 显示加载框
     */
    void showProgressDialog();

    /**
     * 影藏加载框
     */
    void hideProgressDialog();

    /**
     * 显示空布局
     */
    void showEmptyView();

    /**
     * 显示网络出错的布局
     */
    void showNetErrorView();


    /**
     * 获取数据成功
     */
    void showDataSuccess();

    /**
     * 显示吐司
     */
    void showToast(String msg);

    /**
     * 数据加载失败
     */
    void showDataError();
}
