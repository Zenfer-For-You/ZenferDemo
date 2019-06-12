package com.zenfer.demo.network.framwork.Intercepter;

import android.util.Log;

import com.zenfer.demo.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 请求日志拦截
 *
 * @author Zenfer
 * @date 2019/6/11 11:20
 */
public class LogInterceptorImpl implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtil.i("request:" + request.toString()+"\n"+bodyToString(request.body()));
        okhttp3.Response response = chain.proceed(chain.request());
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtil.i("response body:" + content);
        if (response.body() != null) {
            ResponseBody body = ResponseBody.create(mediaType, content);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }

    private static String bodyToString(final RequestBody request) {
        Buffer buffer = null;
        try {
            final RequestBody copy = request;
            buffer = new Buffer();
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            Log.getStackTraceString(e);
            return "";
        } finally {
            if (buffer != null) {
                buffer.close();
            }
        }
    }
}
