package com.zenfer.demo.network.framwork;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * 网络回调
 *
 * @author Zenfer
 * @date 2019/6/11 10:30
 */
public class NetWorkCallBack {

    /**
     * 回调
     */
    private BaseCallBack callBack;
    /**
     * 接口标签
     */
    private String tag;

    public NetWorkCallBack(@Nullable BaseCallBack callBack) {
        this.callBack = callBack;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public NetWorkSubscriber getNetWorkSubscriber() {
        return netWorkSubscriber;
    }

    private NetWorkSubscriber netWorkSubscriber = new NetWorkSubscriber() {

        @Override
        public void onStart() {
            if (callBack != null) {
                callBack.onBegin(tag);
            }
        }

        @Override
        public void onCompleted() {
            if (callBack != null) {
                callBack.onEnd(tag);
            }
        }

        @Override
        public void onError(Throwable e) {
            try {
                if (callBack != null) {
                    callBack.onFail(tag, NetworkErrorHandler.getException(e));
                    callBack.onEnd(tag);
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
                    if (callBack != null) {
                        callBack.onSuccess(tag, result);
                    }
                } else {
                    //请求失败
                    String message = !TextUtils.isEmpty(result.getError()) ? result.getError() : "未知错误";
                    int errorCode = 0;
                    if (callBack != null) {
                        callBack.onFail(tag, new NetWordException(null, errorCode, message));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (callBack != null) {
                    callBack.onEnd(tag);
                }
            }
        }
    };

    public interface BaseCallBack {
        /**
         * 请求成功
         *
         * @param result 返回参数
         */
        void onSuccess(String tag, NetWordResult result);

        /**
         * 请求失败
         *
         * @param msg 错误信息
         */
        void onFail(String tag, NetWordException msg);

        /**
         * 请求开始
         */
        void onBegin(String tag);

        /**
         * 请求结束
         */
        void onEnd(String tag);
    }

}
