package com.silence.music.base.ibase;

/**
 * view层的顶级接口
 * @author tinlone
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
