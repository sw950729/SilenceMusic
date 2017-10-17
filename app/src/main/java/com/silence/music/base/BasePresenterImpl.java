package com.silence.music.base;

import android.content.Context;

import com.silence.music.base.ibase.IBasePresenter;
import com.silence.music.base.ibase.IView;


/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class BasePresenterImpl<V extends IView> implements IBasePresenter {


    public V mView;
    public Context context;

    public void attachView(V view, Context context) {
        this.mView = view;
        this.context = context;
    }

}
