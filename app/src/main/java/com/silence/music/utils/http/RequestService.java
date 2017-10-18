package com.silence.music.utils.http;


import com.silence.music.bean.TestBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 *
 * @author tinlone
 * @date 2017/6/6 0006
 */

public interface RequestService {

    //    http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=method=baidu.ting.song.playAAC&songid=877578
    @GET("v1/restserver/ting")
    Observable<TestBean> getInfo(@QueryMap() Map<String, Object> params);
}
