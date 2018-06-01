package com.silence.music.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Silence on 2017/4/24.
 */
public class Utils {

    /**
     * 判断是否是快速重复点击
     */
    private static long lastClickTime;
    private static String[] clickTag = new String[5];
    private static long[] clickTagTime = new long[5];
    private static int clickTagCunt = 0;

    public static boolean isFastClick() {//没有tag,会共用时间
        return isFastClick(800);
    }

    public static boolean isFastClick(int millisSecond) {//没有tag,会共用时间
        long time = System.currentTimeMillis();
        if (time - lastClickTime < millisSecond) {
            lastClickTime = time;
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    public static boolean isFastClick(@NonNull String tag) {//传唯一标识如className
        return isFastClick(tag, 800);
    }

    public static boolean isFastClick(@NonNull String tag, int millisSecond) {//传唯一标识如className
        long time = System.currentTimeMillis();
        boolean isSaved = false;
        for (int i = 0; i < clickTag.length; i++) {
            if (tag.equals(clickTag[i])) {
                if (time - clickTagTime[i] < millisSecond) {
                    clickTagTime[i] = time;
                    isSaved = true;
                } else {
                    clickTagTime[i] = time;
                    isSaved = false;
                }
            }
        }
        if (!isSaved) {
            clickTag[clickTagCunt] = tag;
            clickTagTime[clickTagCunt] = time;
            clickTagCunt++;
        }
        return isSaved;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (context == null) {
            return 1;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 判断列表是否为空
     */
    public static boolean isListNotEmpty(List<?> list) {
        return list != null && list.size() > 0;
    }
}
