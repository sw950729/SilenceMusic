package com.silence.music.network;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
            String s = message.substring(0, 1);
            if ("{".equals(s) || "[".equals(s)) {
                Log.i("RxNetWork", "Retrofit====Message:" + message);
            }

            try {
                JSONObject jsonObject = new JSONObject(message);
                if (jsonObject.has("resCode")) {
                    String resCode = jsonObject.optString("resCode");
                    if (resCode.equals("-1")) {
                        System.exit(0);
                        Log.i("RxNetWork", "manager-->>" + resCode);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
