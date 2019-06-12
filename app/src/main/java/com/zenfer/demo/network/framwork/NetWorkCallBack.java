package com.zenfer.demo.network.framwork;

import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertPathValidatorException;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import retrofit2.adapter.rxjava.HttpException;

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

    public NetWorkCallBack(BaseCallBack callBack) {
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
            NetWordException ex;
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                ex = new NetWordException(e, httpException.code());
                switch (ex.getCode()) {
                    case 302:
                        ex.setMessage("网络错误");
                        break;
                    case 401:
                        ex.setMessage("未授权的请求");
                        break;
                    case 403:
                        ex.setMessage("禁止访问");
                        break;
                    case 404:
                        ex.setMessage("服务器地址未找到");
                        break;
                    case 405:
                        ex.setMessage("请求方法不允许");
                        break;
                    case 408:
                        ex.setMessage("请求超时");
                        break;
                    case 417:
                        ex.setMessage("接口处理失败");
                        break;
                    case 500:
                        ex.setMessage("服务器出错");
                    case 502:
                        ex.setMessage("无效的请求");
                        break;
                    case 503:
                        ex.setMessage("服务器不可用");
                        break;
                    case 504:
                        ex.setMessage("网关响应超时");
                        break;
                    default:
                        if (TextUtils.isEmpty(ex.getMessage())) {
                            ex.setMessage(e.getMessage());
                        } else if (TextUtils.isEmpty(ex.getMessage()) && e.getLocalizedMessage() != null) {
                            ex.setMessage(e.getLocalizedMessage());
                        } else if (TextUtils.isEmpty(ex.getMessage())) {
                            ex.setMessage("未知错误");
                        }
                        break;
                }

            } else if (!(e instanceof JsonParseException) && !(e instanceof JSONException) && !(e instanceof ParseException)) {
                if (e instanceof ConnectException) {
                    ex = new NetWordException(e, 1002);
                    ex.setMessage("连接失败");
                } else if (e instanceof SSLHandshakeException) {
                    ex = new NetWordException(e, 1005);
                    ex.setMessage("证书验证失败");
                } else if (e instanceof CertPathValidatorException) {
                    ex = new NetWordException(e, 1007);
                    ex.setMessage("证书路径没找到");
                } else if (e instanceof SSLPeerUnverifiedException) {
                    ex = new NetWordException(e, 1007);
                    ex.setMessage("无有效的SSL证书");
                } else if (e instanceof ConnectTimeoutException) {
                    ex = new NetWordException(e, 1006);
                    ex.setMessage("连接超时");
                } else if (e instanceof SocketTimeoutException) {
                    ex = new NetWordException(e, 1006);
                    ex.setMessage("连接超时");
                } else if (e instanceof ClassCastException) {
                    ex = new NetWordException(e, 1008);
                    ex.setMessage("类型转换出错");
                } else if (e instanceof NullPointerException) {
                    ex = new NetWordException(e, -100);
                    ex.setMessage("数据有空");
                } else if (e instanceof UnknownHostException) {
                    ex = new NetWordException(e, 404);
                    ex.setMessage("服务器地址未找到,请检查网络或Url");
                } else {
                    ex = new NetWordException(e, 1000);
                    ex.setMessage(e.getMessage());
                }
            } else {
                ex = new NetWordException(e, 1001);
                ex.setMessage("解析错误");
            }

            try {
                if (callBack != null) {
                    callBack.onFail(tag, ex);
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
