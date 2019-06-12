package com.zenfer.demo.network.framwork;

import com.zenfer.demo.network.api.Api;
import com.zenfer.demo.network.api.ApiEnum;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public static void init(@Nonnull String baseUrl) {
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
        try {
            netWorkCallBack.setTag(tag);
            addObservable(Api.get(tag, params), netWorkCallBack.getNetWorkSubscriber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Rxjava链式调度
     */
    private static <M> void addObservable(Observable<M> observable, Subscriber<M> subscriber) {
        RxUtils.getInstance().addSubscription(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
