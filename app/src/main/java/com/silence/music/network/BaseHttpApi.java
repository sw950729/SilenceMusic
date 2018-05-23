package com.silence.music.network;


import com.silence.music.bean.NewsBean;
import com.silence.music.bean.NewsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Silence
 * @date 2018/3/21.
 */

public interface BaseHttpApi {

    @GET("latest")
    Observable<NewsBean> getNews();

    @GET("{id}")
    Observable<NewsListBean> getNewsList(@Path("id") String id);
}
