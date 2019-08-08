package com.yushibao.network.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求接口的共有参数
 *
 * @author Zenfer
 * @date 2017/6/23
 */
public class NetCommonParams {
    /**
     * 所有接口的共有参数
     */
    public static Map<String, String> commonParam() {
        Map<String, String> map = new HashMap<>(1);
//      map.put("token", token);
        return map;
    }

    public static Map<String, Object> commonObjectParam() {
        Map<String, Object> map = new HashMap<>(1);
//      map.put("token", token);
        return map;
    }
}
