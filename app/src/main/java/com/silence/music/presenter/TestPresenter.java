package com.silence.music.presenter;

import com.silence.music.base.BasePresenterImpl;
import com.silence.music.bean.TestBean;
import com.silence.music.contract.TestContract;
import com.silence.music.model.TestModel;
import com.silence.music.model.imodel.ITestModel;
import com.silence.music.utils.http.AsyncCallBack;

import java.util.HashMap;

/**
 * @author tinlone
 * @date 2017/10/17 0017.
 */

public class TestPresenter extends BasePresenterImpl<TestContract.ITestView> implements TestContract.ITestPresenter {

    ITestModel model;

    public TestPresenter() {
        model = new TestModel();
    }

    @Override
    public void getTestData(long id) {

        //baidu.ting.song.lry&songid=402308
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("format", "json");
        params.put("calback", "");
        params.put("from", "webapp_music");
        params.put("method", "baidu.ting.song.lry");
        params.put("songid", id);

        model.getTestData(params,new AsyncCallBack<TestBean>() {
            @Override
            public void onSuccess(TestBean result) {
                mView.showDataSuccess(result);
            }

            @Override
            public void onFailed(String msg, int error_code) {
                mView.showDataError(msg, error_code);
            }
        });
    }
}
