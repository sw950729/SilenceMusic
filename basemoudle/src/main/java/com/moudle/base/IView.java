package com.moudle.base;


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
     * 显示吐司
     */
    void showToast(String msg);

    void showNetError();
}
