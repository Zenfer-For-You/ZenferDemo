package com.zenfer.demo.network.framwork;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Rx工具类,为了控制当前页面的网络请求
 *
 * @author Zenfer
 * @date 2019/6/11 11:23
 */
public class RxUtils {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private RxUtils() {
    }

    public static RxUtils getInstance() {
        return new RxUtils();
    }

    /**
     * 清除所有网络请求
     */
    public void clearSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }

    /**
     * 清除当前界面的网络请求
     */
    public void unSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加网络请求
     */
    public void addSubscription(Subscription subscription) {
        if (compositeSubscription != null) {
            compositeSubscription.add(subscription);
        }
    }

}
