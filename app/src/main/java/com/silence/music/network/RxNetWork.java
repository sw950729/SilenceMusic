package com.silence.music.network;

import android.text.TextUtils;
import android.util.Log;

import com.angel.music.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Silence
 * @date 2018/3/21.
 */

public class RxNetWork {

    private static BaseHttpApi baseHttpApi;

    private static OkHttpClient mOkHttpClient;

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static final int DEFAULT_TIMEOUT = 15;

    static {
        getOkHttpClient();
    }

    public static BaseHttpApi getObserverHttp() {

        if (baseHttpApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .client(mOkHttpClient)
                    .baseUrl(BaseUrl.BASE_HTTP)
                    .build();
            baseHttpApi = retrofit.create(BaseHttpApi.class);
        }
        return baseHttpApi;
    }

    public static BaseHttpApi getObserverHttps() {
        if (baseHttpApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .client(mOkHttpClient)
                    .baseUrl(BaseUrl.BASE_HTTPS)
                    .build();
            baseHttpApi = retrofit.create(BaseHttpApi.class);
        }
        return baseHttpApi;
    }

    public static BaseHttpApi getZhihuHttp() {

        if (baseHttpApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .client(mOkHttpClient)
                    .baseUrl(BaseUrl.ZHIHU_HTTP)
                    .build();
            baseHttpApi = retrofit.create(BaseHttpApi.class);
        }
        return baseHttpApi;
    }

    private static void getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RxNetWork.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(getHttpLoggingInterceptor())
                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .retryOnConnectionFailure(true)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            if (TextUtils.isEmpty(message)) {
                return;
            }
            if (BuildConfig.DEBUG) {
                Log.i("RxNetWork", "Retrofit====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
