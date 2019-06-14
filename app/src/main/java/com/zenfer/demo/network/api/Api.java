package com.zenfer.demo.network.api;

import com.zenfer.demo.network.framwork.NetWordResult;
import com.zenfer.demo.network.framwork.Network;
import com.zenfer.demo.network.framwork.RequestBodyUtil;

import rx.Observable;

/**
 * 获取 api 请求的 Observable<NetWordResult> 对象
 *
 * @author Zenfer
 * @date 2019/6/11 15:20
 */
public class Api {

    /**
     * 获取对应的 Observable<NetWordResult>
     *
     * @param tag    接口标签{@link ApiEnum}
     * @param params 参数
     * @return 请求接口的Observable
     */
    public static Observable<NetWordResult> get(@ApiEnum String tag, Object params) throws Exception {
        switch (tag) {
            case ApiEnum.CODE_LOGIN:
                return Network.getInstance().getApi(ApiService.class).codeLogin(RequestBodyUtil.createMapRequestBody(params));
            case ApiEnum.CONFIG:
                return Network.getInstance().getApi(ApiService.class).config(RequestBodyUtil.createMapParams(params));
            default:
                throw new Exception("can not match the request tag \"" + tag + "\"");
        }
    }

}
