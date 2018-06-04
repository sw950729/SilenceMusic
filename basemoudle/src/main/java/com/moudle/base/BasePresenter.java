package com.moudle.base;


import com.trello.rxlifecycle.LifecycleProvider;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Silence on 2018/3/19.
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> view;
    private LifecycleProvider<V> provider;

    @Override
    public void attchView(V view, LifecycleProvider<V> provider) {
        this.view = new WeakReference<>(view);
        this.provider = provider;
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

    public <T> Observable.Transformer<T, T> bindLife() {
        return observable -> observable.compose(provider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
