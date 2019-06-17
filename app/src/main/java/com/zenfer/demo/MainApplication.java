package com.zenfer.demo;

import android.support.multidex.MultiDexApplication;

import com.zenfer.demo.network.framwork.NetUtil;
import com.zenfer.demo.util.AutoSizeUtil;
import com.zenfer.demo.util.LeakCanaryUtil;
import com.zenfer.demo.util.ResourceUtil;

/**
 * Application
 *
 * @author Zenfer
 * @date 2019/6/11 11:41
 */
public class MainApplication extends MultiDexApplication {

    private static MainApplication INSTANCE = null;

    public static MainApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        INSTANCE = this;
        //初始化网络请求
        NetUtil.init(BuildConfig.SERVER_URL, BuildConfig.DEBUG);
        //资源工具类初始化
        ResourceUtil.init(this);
        //屏幕适配
        AutoSizeUtil.initAutoSize(this);

        if (BuildConfig.DEBUG) {
            LeakCanaryUtil.setupLeakCanary(this);
        }
    }

}
