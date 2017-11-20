package com.silence.music.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.angel.music.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * @author Tinlone
 */
public class L {

    public static final String TAG = "SilenceMusic";
    private static boolean showLog = BuildConfig.DEBUG;

    private L() {
    }

    public static void d(Object object) {
        if (showLog) {
            android.util.Log.d(TAG, valueOf(object));
        }
    }

    public static void d(String tag, Object object) {
        if (showLog) {
            android.util.Log.d(tag, valueOf(object));
        }
    }

    public static void i(Object object) {
        if (showLog) {
            android.util.Log.i(TAG, valueOf(object));
        }
    }

    public static void i(String tag, Object object) {
        if (showLog) {
            android.util.Log.i(tag, valueOf(object));
        }
    }

    public static void i(String msg, Object... objs) {
        if (showLog) {
            android.util.Log.i(TAG, valueOf(msg, objs));
        }
    }

    public static void i(String tag, String msg, Object... objs) {
        if (showLog) {
            android.util.Log.i(tag, valueOf(msg, objs));
        }
    }

    public static void e(Object object) {
        if (showLog) {
            android.util.Log.e(TAG, valueOf(object));
        }
    }

    public static void e(String msg, Object... objs) {
        if (showLog) {
            android.util.Log.e(TAG, valueOf(msg, objs));
        }
    }

    public static void e(String tag, Object object) {
        if (showLog) {
            android.util.Log.e(tag, valueOf(object));
        }
    }

    public static void w(Object object) {
        if (showLog) {
            android.util.Log.w(TAG, valueOf(object));
        }
    }

    public static void w(String tag, Object object) {
        if (showLog) {
            android.util.Log.w(tag, valueOf(object));
        }
    }

    public static void th(Throwable object) {
        if (showLog) {
            Log.e(TAG, "这里有异常！！", object);
        }
    }

    public static String valueOf() {
        return "";
    }

    public static String valueOf(Object object) {
        if (object instanceof Throwable) {
            return getStackTraceString((Throwable) object);
        }
        return object == null ? "null" : object.toString();
    }

    public static String valueOf(String msg, Object... objs) {
        try {
            return String.format(msg, objs);
        } catch (Exception e) {
            e.printStackTrace();
            return "---WARNING--- 占位符对应格式有误:" + msg;
        }
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    private static void heapSize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = 0;
        if (manager != null) {
            heapSize = manager.getMemoryClass();
        }
        i(valueOf("系统分配内存大小为：" + heapSize + " M "));
        i(valueOf("This app heap-size = " + heapSize + " M "));
    }

}
