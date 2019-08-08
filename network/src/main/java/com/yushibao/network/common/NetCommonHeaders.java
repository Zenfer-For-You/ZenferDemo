package com.yushibao.network.common;

import android.text.TextUtils;

import com.yushibao.employer.Constants;
import com.yushibao.employer.util.UserUtil;

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
        map.put("role", "1");
        String token = UserUtil.getInstance().getToken();
        if (!TextUtils.isEmpty(token) && !Constants.ParamKey.TEXT_NULL.equals(token)) {
            map.put("authorization", "Bearer " + token);
        }
        return map;
    }
}
