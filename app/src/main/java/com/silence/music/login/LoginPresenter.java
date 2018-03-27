package com.silence.music.login;



import com.silence.music.base.BasePresenter;
import com.silence.music.network.RxNetWork;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Silence on 2018/3/19.
 */

public class LoginPresenter extends BasePresenter<ILoginContract.ILoginView> implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView view;

    public LoginPresenter(ILoginContract.ILoginView view) {
        this.view = view;
    }

    @Override
    public void login() {
        RxNetWork.getObserveHttp().getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getView().showDataSuccess(loginBean);
                },throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getView().showDataError();
                });
    }
}
