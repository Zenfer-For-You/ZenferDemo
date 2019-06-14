package com.zenfer.demo.network.framwork;


import com.google.gson.Gson;
import com.zenfer.demo.network.framwork.Intercepter.AppendHeaderParamInterceptorImpl;
import com.zenfer.demo.network.framwork.Intercepter.LogInterceptorImpl;

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
        public static final Network NETWORK = new Network();
    }

    private Map<String, Object> servicesMap = new ConcurrentHashMap<>();

    /**
     * Base URL
     */
    public static String BASE_URL;

    /**
     * 超时时长
     */
    private static final int DEFAULT_TIMEOUT = 10;

    private static final Converter.Factory GSON_CONVERTER_FACTORY = GsonConverterFactory.create(new Gson());
    private static final CallAdapter.Factory RX_JAVA_CALL_ADAPTER_FACTORY = RxJavaCallAdapterFactory.create();


    public <T> T getApi(Class<T> clazz) {
        T service = (T) servicesMap.get(clazz.getCanonicalName());
        if (service == null) {
            service = getRetrofit(BASE_URL).create(clazz);
            servicesMap.put(clazz.getCanonicalName(), service);
        }
        return service;
    }

    private static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(new AppendHeaderParamInterceptorImpl())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(new LogInterceptorImpl())
                        .build())
                .baseUrl(baseUrl)
                .addConverterFactory(GSON_CONVERTER_FACTORY)
                .addCallAdapterFactory(RX_JAVA_CALL_ADAPTER_FACTORY)
                .build();
    }

    /**
     * 生成Rxjava链式调度
     */
    public static <T> void addObservable(Observable<T> observable, Subscriber<T> subscriber) {
        RxUtils.getInstance().addSubscription(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
