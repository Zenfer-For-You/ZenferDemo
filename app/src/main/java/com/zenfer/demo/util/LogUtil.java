package com.zenfer.demo.util;


import android.support.multidex.BuildConfig;

import com.orhanobut.logger.Logger;


/**
 * 日志工具类
 *
 * @author Zenfer
 * @date 2019/6/10 17:00
 */
public class LogUtil {
    private static String TAG = "yushibao";

    public static void initLogger() {
        //特殊log初始化
        Logger.init(TAG);
    }

    public static void d(String msg) {
        if (!BuildConfig.DEBUG)
            Logger.d(msg);
    }

    public static void i(String msg) {
        if (!BuildConfig.DEBUG)
            Logger.i(msg);
    }

    public static void e(String msg) {
        if (!BuildConfig.DEBUG)
            Logger.e(msg);
    }

    public static void json(String json) {
        if (!BuildConfig.DEBUG)
            Logger.json(json);
    }
}
