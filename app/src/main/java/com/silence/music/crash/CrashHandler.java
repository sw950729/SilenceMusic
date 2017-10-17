package com.silence.music.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2016/7/19
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 日志过期清理时间周期
     * 设置过期时间(单位：天)
     */
    private static final int expirationDate = 7;

    /**
     * 错误日志存放路径
     */
    private static final String ERROR_PATH = "/zhiyi/crash/student_error/";
    /**
     * Debug Log tag
     */
    public static final String TAG = "CrashHandler";
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;
    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    // private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";


    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    public CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        deleteExpirationFile();
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //Sleep一会后结束程序
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Error : ", e);
            }
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
//        //上传错误日志到友盟
//        MobclickAgent.reportError(mContext, ex);
        if (ex == null) {
            Log.w(TAG, "handleException --- ex==null");
            return true;
        }
        final String msg = ex.getLocalizedMessage();
        if (msg == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常，即将退出:\n" + msg, Toast.LENGTH_SHORT);
//                MsgPrompt.showMsg(mContext, "程序出错啦", msg+"\n点确认退出");
                Looper.loop();
            }
        }.start();

        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put(VERSION_NAME, versionName);
                infos.put(VERSION_CODE, versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        String product_info = "";
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
            //当前设备的相关信息
            if (key != null && key.toUpperCase().equals("FINGERPRINT")) {
                product_info = value.replace("/", "-").replace(":", "-").replace("\\", "-");
            }
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = product_info + "-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getPath() + ERROR_PATH;
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
//			Intent intent = new Intent(mContext,UploadErrorLogService.class);
//			mContext.startService(intent);
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    private static void deleteExpirationFile() {
        String path = Environment.getExternalStorageDirectory().getPath() + ERROR_PATH;
        if (!new File(path).exists()) {
            return;
        }
        long expirationTime = expirationDate * 24 * 3600 * 1000;
        long nowTime = System.currentTimeMillis();
        List<File> deleteList = new ArrayList<File>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                if (f.exists()) {
                    long time = f.lastModified();
                    if (nowTime - time >= expirationTime) {
                        deleteList.add(f);
                    }
                }
            }
            for (File f : deleteList) {
                f.delete();
            }
        }
    }
}

