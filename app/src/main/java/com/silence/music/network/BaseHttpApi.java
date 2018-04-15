package com.silence.music.network;

import com.silence.music.bean.LoginBean;


import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Silence
 * @date 2018/3/21.
 */

public interface BaseHttpApi {

    @GET("login.json")
    Observable<LoginBean> getApi();
}
