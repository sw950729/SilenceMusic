package com.silence.music.utils.http;

import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Administrator
 * @date 2017/6/8 0008
 */

public class Client {

    private static RequestService service;

    public static RequestService getService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new MyInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            service = retrofit.create(RequestService.class);
        }
        return service;
    }

    private static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("User-Agent", makeUA())
                    .build();
            Request request = builder.build();
            Log.i("zjl", " MyInterceptor --> intercept # " + request.url().toString());
            Log.i("zjl", "(Client.java:50)--intercept: " + request.headers().toString());
            return chain.proceed(request);
        }
    }
    private static String makeUA() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
