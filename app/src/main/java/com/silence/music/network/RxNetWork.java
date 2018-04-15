package com.silence.music.network;

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

    public static BaseHttpApi getObserveHttp() {

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

    public static BaseHttpApi getObserveHttps() {
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

    private static void getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RxNetWork.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
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
}
