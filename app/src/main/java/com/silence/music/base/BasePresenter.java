package com.silence.music.base;

import java.lang.ref.WeakReference;

/**
 * Created by Silence on 2018/3/19.
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> view;

    @Override
    public void attchView(V view) {
        this.view = new WeakReference<>(view);
    }

    protected V getView() {
        return view == null ? null : view.get();
    }

    protected boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
