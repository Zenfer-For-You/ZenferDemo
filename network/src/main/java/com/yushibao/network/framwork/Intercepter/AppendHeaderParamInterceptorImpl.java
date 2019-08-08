package com.yushibao.network.framwork.Intercepter;

import com.yushibao.employer.network.common.NetCommonHeaders;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * 统一追加Header
 *
 * @author Zenfer
 * @date 2019/6/11 11:05
 */
public class AppendHeaderParamInterceptorImpl implements Interceptor {

    // 1.获取以前的Builder
    // 2.为以前的Builder添加参数
    // 3.生成新的Builder

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Headers.Builder builder = request.headers().newBuilder();
        //添加公共头部
        Map<String, String> headers = NetCommonHeaders.commonParam();
        //统一追加Header参数
        for (String key : headers.keySet()) {
            builder.add(key, headers.get(key));
        }
        Headers newBuilder = builder.build();

        Request newRequest = request.newBuilder()
                .headers(newBuilder)
                .build();

        return chain.proceed(newRequest);
    }
}