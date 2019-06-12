package com.zenfer.demo.network.framwork;

import rx.Subscriber;

/**
 * 网络请求 Rxjava Subscriber
 *
 * @author Zenfer
 * @date 2019/6/11 11:22
 */
public class NetWorkSubscriber extends Subscriber<NetWordResult> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(NetWordResult t) {

    }


}
