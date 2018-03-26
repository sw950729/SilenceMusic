package com.silence.music.login;


import com.silence.music.base.IView;

/**
 * Created by Administration on 2018/3/19.
 */

public class ILoginContract {

    public interface ILoginView extends IView {
        /**
         * 数据加载成功
         */
        void showDataSuccess();
    }

    public interface ILoginPresenter {
        void login(String userName, String password);
    }
}
