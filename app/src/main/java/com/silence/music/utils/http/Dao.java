package com.silence.music.utils.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author zjl ,@date 2017/10/17 0017.
 */

public class Dao<T> {
    private static Dao mHttpDao = null;

    private Dao() {

    }

    public static Dao getInstance() {
        if (mHttpDao == null) {
            mHttpDao = new Dao();
        }
        return mHttpDao;
    }

    public Observer<T> createObserver(final AsyncCallBack<T> callBack) {
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onFailed(e.getMessage(), -1);
                return;
            }

            @Override
            public void onComplete() {

            }
        };
        return observer;

    }

}
