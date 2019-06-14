package com.zenfer.demo.network.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共头部参数
 *
 * @author Zenfer
 * @date 2019/6/11 10:21
 */
public class NetCommonHeaders {
    /**
     * 所有接口的请求头
     */
    public static Map<String, String> commonParam() {
        Map<String, String> map = new HashMap<>(6);
        map.put("channel", "android");
        map.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hZ2VudC50ZXN0LnlzYmpvYi5jb21cL2FwaVwvbG9naW5cL2NvZGUiLCJpYXQiOjE1NjAyNTA0MDEsIm5iZiI6MTU2MDI1MDQwMSwianRpIjoiQjkwbmk4aU9IaU5lTUxyTyIsInN1YiI6MywicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSIsInJvbGUiOiI0In0.B_-z54tevbFredwoHxTYw4owNDyJzocsFtaCmc8X8Gs");
        map.put("role", "4");

        return map;
    }
}
