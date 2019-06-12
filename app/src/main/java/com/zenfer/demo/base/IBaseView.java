package com.zenfer.demo.base;

public interface IBaseView {

    void onBegin(String tag);

    void onEnd(String tag);

    void onSuccess(String tag, Object entry);

    void onFailure(String tag, int code, String error);
}
