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

public class NetworkErrorHandler {

    public static NetWordException getException(Throwable e) {
        NetWordException ex;
        if (e instanceof HttpException) {
            ex = httpException(e);
        } else if (!(e instanceof JsonParseException) && !(e instanceof JSONException) && !(e instanceof ParseException)) {
            ex = classException(e);
        } else {
            ex = new NetWordException(e, 1001);
            ex.setMessage("解析错误");
        }
        return ex;
    }

    private static NetWordException httpException(Throwable e) {
        NetWordException ex;
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
        return ex;
    }

    private static NetWordException classException(Throwable e) {
        NetWordException ex;
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
        return ex;
    }
}
