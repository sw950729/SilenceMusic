package com.silence.music.base;

import com.silence.music.base.ibase.IBasePresenter;
import com.silence.music.base.ibase.IView;


/**
 * @author tinlone
 * @date 2017/10/17
 */

public class BasePresenterImpl<V extends IView> implements IBasePresenter {

    public V mView;

    public void attachView(V view) {
        this.mView = view;
    }

}
