package com.silence.music.network;


import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.NewsListBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Silence
 * @date 2018/3/21.
 */

public interface BaseHttpApi {

    /**
     * banner+日报列表
     */
    @GET("news/latest")
    Observable<NewsBean> getNews();


    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<NewsListBean> getNewsDetail(@Path("id") String id);

    /***
     * 知乎主题
     */
    @GET("themes")
    Observable<ThemesBean> getThemes();

    /***
     * 主题详情
     */
    @GET("theme/{id}")
    Observable<String> getThemeDetail(@Path("id") String id);

    /***
     * 知乎热门
     */
    @GET("news/hot")
    Observable<HotNewsBean> getHotNews();

    /***
     * 知乎专栏
     */
    @GET("sections")
    Observable<SectionBean> getSections();

    /***
     * 专栏详情
     */
    @GET("section/{id}")
    Observable<String> getSectionDetail(@Path("id") String id);
}
