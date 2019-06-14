package com.zenfer.demo.network.upload;

import android.text.TextUtils;

import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.NetWordResult;
import com.zenfer.demo.network.framwork.NetWorkSubscriber;
import com.zenfer.demo.network.framwork.NetworkErrorHandler;
/**
 * 上传回调
 *
 * @author Zenfer
 * @date 2019/6/14 9:37
 */
public class NetworkUploadCallBack {

    private UploadProgressListener listener;

    public NetworkUploadCallBack(UploadProgressListener listener) {
        this.listener = listener;
    }

    public UploadProgressListener getListener() {
        return listener;
    }

    public NetWorkSubscriber getNetWorkSubscriber() {
        return netWorkSubscriber;
    }

    private NetWorkSubscriber netWorkSubscriber = new NetWorkSubscriber() {

        @Override
        public void onStart() {
            if (listener != null) {
                listener.onBegin();
            }
        }

        @Override
        public void onCompleted() {
            if (listener != null) {
                listener.onEnd();
            }
        }

        @Override
        public void onError(Throwable e) {
            try {
                if (listener != null) {
                    listener.onFail(NetworkErrorHandler.getException(e));
                    listener.onEnd();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        @Override
        public void onNext(NetWordResult result) {
            // 此部分需要根据项目具体需求重新定义
            try {
                if (result.getStatus_code() == 200) {
                    if (listener != null) {
                        listener.onSuccess(result);
                    }
                } else {
                    //请求失败
                    String message = !TextUtils.isEmpty(result.getError()) ? result.getError() : "未知错误";
                    int errorCode = 0;
                    if (listener != null) {
                        listener.onFail(new NetWordException(null, errorCode, message));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (listener != null) {
                    listener.onEnd();
                }
            }
        }
    };
}
