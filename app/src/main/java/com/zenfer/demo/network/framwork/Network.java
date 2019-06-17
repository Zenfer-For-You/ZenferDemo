package com.zenfer.demo.network.framwork;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.zenfer.demo.network.framwork.Intercepter.AppendHeaderParamInterceptorImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * retorfit 初始化
 *
 * @author Zenfer
 * @date 2019/6/11 14:16
 */
public class Network {

    public static Network getInstance() {
        return Holder.NETWORK;
    }

    private static class Holder {
        static final Network NETWORK = new Network();
    }

    private Map<String, Object> servicesMap = new ConcurrentHashMap<>();

    /**
     * Base URL
     */
    private String baseUrl;
    /**
     * 是否为 debug 模式
     */
    private boolean isDebug = false;
    /**
     * 超时时长
     */
    private static final int DEFAULT_TIMEOUT = 10;

    private static final Converter.Factory GSON_CONVERTER_FACTORY = GsonConverterFactory.create(new Gson());
    private static final CallAdapter.Factory RX_JAVA_CALL_ADAPTER_FACTORY = RxJavaCallAdapterFactory.create();

    public void init(String baseUrl, boolean isDebug) {
        this.baseUrl = baseUrl;
        this.isDebug = isDebug;
    }

    public <T> T getApi(Class<T> clazz) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalStateException("请初始化网络请求框架");
        }
        T service = (T) servicesMap.get(clazz.getCanonicalName());
        if (service == null) {
            service = getRetrofit(baseUrl).create(clazz);
            servicesMap.put(clazz.getCanonicalName(), service);
        }
        return service;
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(getOkhttpClient(isDebug))
                .baseUrl(baseUrl)
                .addConverterFactory(GSON_CONVERTER_FACTORY)
                .addCallAdapterFactory(RX_JAVA_CALL_ADAPTER_FACTORY)
                .build();
    }

    private OkHttpClient getOkhttpClient(boolean isDebug) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new AppendHeaderParamInterceptorImpl());
        if (isDebug) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    /**
     * 生成Rxjava链式调度
     */
    public static <T> void addObservable(Observable<T> observable, Subscriber<T> subscriber) {
        RxUtils.getInstance().addSubscription(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
