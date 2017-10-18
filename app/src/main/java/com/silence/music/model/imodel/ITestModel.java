package com.silence.music.model.imodel;

import com.silence.music.bean.TestBean;
import com.silence.music.utils.http.AsyncCallBack;

import java.util.HashMap;

/**
 * @author tinlone ,@date 2017/10/17 0017.
 */

public interface ITestModel {
    /**
     * 获取数据的接口
     * @param params
     * @param callBack
     */
    void getTestData(HashMap<String, Object> params, AsyncCallBack<TestBean> callBack);
}
