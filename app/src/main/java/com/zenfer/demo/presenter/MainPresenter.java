package com.zenfer.demo.presenter;

import com.zenfer.demo.base.BasePresenter;
import com.zenfer.demo.base.IBaseView;
import com.zenfer.demo.network.api.NetWorkRequest;
import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.NetWordResult;
import com.zenfer.demo.network.framwork.NetWorkCallBack;

public class MainPresenter extends BasePresenter<IBaseView> {

    public MainPresenter(IBaseView view) {
        super(view);
    }

    public void request() {
        NetWorkRequest.codeLogin("15089526459", "1111", new NetWorkCallBack(new NetWorkCallBack.BaseCallBack() {
            @Override
            public void onSuccess(String tag, NetWordResult result) {
                mView.onSuccess(tag, result);
            }

            @Override
            public void onFail(String tag, NetWordException msg) {
                mView.onFailure(tag, msg.getCode(), msg.getMessage());
            }

            @Override
            public void onBegin(String tag) {
                mView.onBegin(tag);
            }

            @Override
            public void onEnd(String tag) {
                mView.onEnd(tag);
            }
        }));
    }

}
