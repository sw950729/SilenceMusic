package com.silence.music.network;


import com.silence.music.bean.NewsBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Silence
 * @date 2018/3/21.
 */

public interface BaseHttpApi {

    @GET("latest")
    Observable<NewsBean> getNews();
}
