package com.zenfer.demo.network.framwork;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zenfer.demo.network.api.Api;
import com.zenfer.demo.network.api.ApiEnum;

/**
 * 网络请求工具类
 *
 * @author Zenfer
 * @date 2019/6/11 16:23
 */
public class NetUtil {

    /**
     * 初始化网络请求框架
     *
     * @param baseUrl 域名
     */
    public static void init(@NonNull String baseUrl) {
        Network.BASE_URL = baseUrl;
    }

    /**
     * 执行网络请求
     *
     * @param params          请求参数
     * @param tag             接口标签{@link ApiEnum}
     * @param netWorkCallBack 回调
     */
    public static void excute(@Nullable Object params, @ApiEnum String tag, NetWorkCallBack netWorkCallBack) {
        netWorkCallBack.setTag(tag);
        try {
            Network.addObservable(Api.get(tag, params), netWorkCallBack.getNetWorkSubscriber());
        } catch (Exception e) {
            netWorkCallBack.getNetWorkSubscriber().onError(e);
        }
    }
}
