package com.silence.music.model;

import com.silence.music.bean.TestBean;
import com.silence.music.model.imodel.ITestModel;
import com.silence.music.utils.http.AsyncCallBack;
import com.silence.music.utils.http.Client;
import com.silence.music.utils.http.Dao;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zjl ,@date 2017/10/17 0017.
 */

public class TestModel implements ITestModel {

    @Override
    public void getTestData(HashMap<String, Object> params,AsyncCallBack<TestBean> callBack){
        Client.getService()
                .getInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Dao.getInstance().createObserver(callBack));

    }
}
