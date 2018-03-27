package com.silence.music.base;

import com.trello.rxlifecycle.LifecycleProvider;

/**
 * Created by Silence on 2018/3/19.
 */

public interface IPresenter<V extends IView> {

    void attchView(V view, LifecycleProvider<V> provider);

    void detachView();
}
