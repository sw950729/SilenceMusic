package com.silence.music.login;


import com.silence.music.base.BasePresenter;

/**
 * Created by Silence on 2018/3/19.
 */

public class LoginPresenter extends BasePresenter<ILoginContract.ILoginView> implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView view;

    public LoginPresenter(ILoginContract.ILoginView view) {
        this.view = view;
    }

    @Override
    public void login(String userName, String password) {
        if (userName.equals("angel") && password.equals("123")) {
            if (!isViewAttached()) {
                return;
            }
            getView().showDataSuccess();
        } else {
            if (!isViewAttached()) {
                return;
            }
            getView().showDataError();
        }
    }
}
