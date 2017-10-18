package com.silence.music;

import android.app.Application;

import com.silence.music.crash.CrashHandler;

/**
 * @author tinlone
 * @date 2017/10/17 0017.
 */

public class MyApplication extends Application {

    public static MyApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    public static MyApplication getInstance(){
        return mApp;
    }
}
