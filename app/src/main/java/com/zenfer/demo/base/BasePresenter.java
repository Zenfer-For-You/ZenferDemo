package com.zenfer.demo.base;

public class BasePresenter<V extends IBaseView> {
    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        mView = null;
    }
}
