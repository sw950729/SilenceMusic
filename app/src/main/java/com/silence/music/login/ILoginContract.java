package com.silence.music.login;


import android.app.Activity;
import android.content.Context;

import com.silence.music.base.IView;
import com.silence.music.bean.LoginBean;

/**
 * Created by Administration on 2018/3/19.
 */

public class ILoginContract {

    public interface ILoginView extends IView {
        /**
         * 数据加载成功
         */
        void showDataSuccess(LoginBean loginBean);
    }

    public interface ILoginPresenter {
        void login();
    }
}
