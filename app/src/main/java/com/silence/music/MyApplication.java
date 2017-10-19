package com.silence.music;

import android.app.Application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.angel.music.BuildConfig;
import com.silence.music.bean.BugEnvirInfoBean;
import com.silence.music.crash.CrashHandler;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author tinlone
 * @date 2017/10/17 0017.
 */
public class MyApplication extends Application {

    public static MyApplication mApp;
    private PackageInfo packageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        initBugly();
    }
    /**
     * bugly初始化设置
     */
    private void initBugly() {
        //===============bugly错误日志加入-start ====================
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        try {
            packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));

        //Bugly默认读取AndroidManifest.xml文件中VersionName、Package信息。若您有自己的版本或渠道设定需求，可通过该接口修改。
        if (packageInfo != null) {
            strategy.setAppChannel("Silence Music");  //设置渠道
            strategy.setAppVersion(packageInfo.versionName);      //App的版本
            strategy.setAppPackageName(packageName);  //App的包名
            //用户手机环境信息
            BugEnvirInfoBean bugEnvirInfo = new BugEnvirInfoBean();
            bugEnvirInfo.setAppName(packageInfo.applicationInfo.name);
            bugEnvirInfo.setAppointedType(Build.MODEL);
            bugEnvirInfo.setAppVersion(packageInfo.versionName);
            bugEnvirInfo.setBrand(Build.BRAND);
            bugEnvirInfo.setCpu(Build.CPU_ABI);
            bugEnvirInfo.setErrContent("Silence Music出错啦！！！");
            bugEnvirInfo.setOsVersion(Build.VERSION.RELEASE);
            bugEnvirInfo.setPackPath(packageInfo.packageName);
            bugEnvirInfo.setType("android");
            CrashReport.putUserData(context, "BugEnvirInfoBean", JSON.toJSONString(bugEnvirInfo));
        }
        // 初始化Bugly
        if (BuildConfig.BASE_NUMBER.equals("2")) {//正式环境下不输出log
            CrashReport.initCrashReport(context, "6ee7dd5f6a", false, strategy);
        } else {
            CrashReport.initCrashReport(context, "6ee7dd5f6a", true, strategy);
        }
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);
        //===============bugly错误日志加入-end   ====================
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    public static MyApplication getInstance(){
        return mApp;
    }
}
