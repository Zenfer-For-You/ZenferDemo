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
        map.put("authorization", "Bearer ");
        map.put("role", "4");

        return map;
    }
}
