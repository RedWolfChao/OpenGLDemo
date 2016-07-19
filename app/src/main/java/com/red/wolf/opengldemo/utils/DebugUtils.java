package com.red.wolf.opengldemo.utils;

import android.util.Log;

/**
 * Log的封装类
 * <p/>
 * Tag - - >RedWolf
 */
public class DebugUtils {
    //  Debug的开关
    private static boolean bDebug = true;

    // 打印信息
    public static void LogI(String str) {
        if (bDebug) {
            Log.i("RedWolf", str + "");
        }
    }

    //  打印错误
    public static void LogE(String str, Throwable e) {
        if (bDebug) {
            Log.e("RedWolf", "- ->Error<- -" + str, e);
        }
    }
}
