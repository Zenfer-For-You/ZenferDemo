package com.zenfer.demo.network.api;


import com.zenfer.demo.network.framwork.NetUtil;
import com.zenfer.demo.network.framwork.NetWorkCallBack;

import java.util.Map;

import static com.zenfer.demo.network.common.NetCommonParams.commonParam;


/**
 * 网络请求方法
 *
 * @author Zenfer
 * @date 2019/6/11 16:10
 */
public class NetWorkRequest {

    /**
     * 登录
     */
    public static void codeLogin(String mobile, String code, NetWorkCallBack netWorkCallBack) {
        Map<String, String> map = commonParam();
        map.put("mobile", mobile);
        map.put("code", code);
        NetUtil.excute(map, ApiEnum.CODE_LOGIN, netWorkCallBack);
    }

    /**
     * 基本参数配置
     *
     * @param netWorkCallBack 回调
     */
    public static void config(NetWorkCallBack netWorkCallBack) {
        NetUtil.excute(null, ApiEnum.CONFIG, netWorkCallBack);
    }

}
