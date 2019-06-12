package com.zenfer.demo.network.common;

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
        Map<String, String> map = new HashMap<>();
//        UserModel user = UserUtil.user();
//        if (user != null) {
//            map.put("userId", String.valueOf(user.getUserId()));
//            String token = user.getToken();
//            if (StringUtil.isNotBlank(token))
//                map.put("x-token", token);
//        }

//        map.putAll(commonPhoneInfo());
        return map;
    }

    /**
     * 用户未登录的状态下,接口请求用这个参数
     * 手机信息相关参数
     */
    public static Map<String, String> commonPhoneInfo() {
        Map<String, String> map = new HashMap<>();
//        //手机UDID
//        map.put("udid", PhoneUtils.getDeviceId());
//        //手机wifi的Mac地址
//        map.put("mac", SystemUtil.getMacAddress(MainApplication.getInstance()));
//        //用户手机类型:android
//        map.put("os", OsEnum.ANDROID.getType() + "");
//        String appChannel = SystemUtil.getAppMetaData(MainApplication.getInstance());
//        //渠道名
//        if (!StringUtil.isBlank(appChannel)) {
//            map.put("channel", appChannel);
//        }

        return map;
    }
}
