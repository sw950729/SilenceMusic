package com.silence.music.base;

/**
 * Created by Silence on 2018/3/19.
 */

public interface IPresenter<V extends IView> {

    void attchView(V view);

    void detachView();
}
