package com.silence.music.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Leo on 2016/7/15
 */
public class ActivityStack {

    static Stack<Activity> stack;
    static ActivityStack instace;

    private ActivityStack() {
        stack = new Stack<Activity>();
    }

    public static ActivityStack getInstace() {
        if (instace == null) {
            instace = new ActivityStack();
        }
        return instace;
    }

    /**
     * 添加Activity堆栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        stack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后压入的）
     *
     * @return
     */
    public Activity getCurrentActivity() {
        Activity activity = stack.lastElement();
        return activity;
    }

    /**
     * 判断栈内是否存在某个Activity
     */
    public boolean isActivityExist(Class<?> clazz){
        List<Class<? extends Activity>> clazzList = new ArrayList<>();
        if (stack != null){
            for (Activity activity : stack) {
                Class<? extends Activity> aClass = activity.getClass();
                clazzList.add(aClass);
            }
            if (clazzList.contains(clazz)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 移除指定activity并finish
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            stack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除指定类名的activity
     * @param clazz
     */
    public void removeActivity(Class<?> clazz) {
//        for (Activity activity : stack) {
//            if (activity.getClass().equals(clazz)) {
//                removeActivity(activity);
//            }
//        }
        for (int i=stack.size()-1;i>0;i--){
            Activity activity=stack.get(i);
            if (activity.getClass().equals(clazz)){
                removeActivity(activity);
            }
        }
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = stack.lastElement();
        removeActivity(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            removeActivity(stack.get(i));
        }
        stack.clear();
    }

    /**
     * 获取指定的Activity
     *
     */
    public Activity getActivity(Class<?> cls) {
        if (stack != null)
            for (Activity activity : stack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAll();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            stack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        try {
            for (Activity activity : stack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isActivityTop(String activityName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(activityName);
    }

}
