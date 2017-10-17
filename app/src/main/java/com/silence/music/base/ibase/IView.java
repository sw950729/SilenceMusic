package com.silence.music.base.ibase;

/**
 * Created by Administrator on 2016/10/11 0011.
 * view层的顶级接口
 * The top interface of the view layer is currently no function
 */

public interface IView<V> {

    /**
     * 数据加载失败
     */
    void showDataError(String errorMessage, int tag);

    /**
     * 数据加载成功
     */
    void showDataSuccess(V datas);

    /**
     * 显示加载进度
     */
    void loading();

    /**
     * 隐藏加载进度
     */
    void hiding();

    /**
     * 显示无网络视图
     */
    void showNetErrorView();

    /**
     * 显示没有加载到内容的视图
     */
    void showEmptyView(String msg, int img);

}
