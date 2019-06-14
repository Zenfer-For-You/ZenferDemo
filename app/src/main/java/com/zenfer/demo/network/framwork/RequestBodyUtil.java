package com.zenfer.demo.network.framwork;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 创建请求的body
 *
 * @author Zenfer
 * @date 2019/6/11 16:15
 */
public class RequestBodyUtil {

    /**
     * 创建 Map 请求参数
     *
     * @param params 请求参数
     */
    public static Map<String, String> createMapParams(@Nullable Object params) {
        Map<String, String> data;
        if (!(params instanceof Map)) {
            data = new HashMap<>(1);
        } else {
            data = (Map<String, String>) params;
        }
        return data;
    }


    /**
     * 创建实体类参数的请求body
     *
     * @param params 请求参数
     * @return 实体类参数RequestBody
     */
    public static RequestBody createJsonRequestObject(@Nullable Object params) {
        if (params == null) {
            params = new Object();
        }
        String content = GsonUtils.toJson(params);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
    }

    /**
     * 创建Map参数的请求Body
     *
     * @return Map参数RequestBody
     */
    public static RequestBody createMapRequestBody(@Nullable Object mapData) {
        Map data;
        if (!(mapData instanceof Map)) {
            data = new HashMap<>(1);
        } else {
            data = (Map) mapData;
        }
        JsonObject mData = new JsonObject();
        Set<String> keySets = data.keySet();
        for (String key : keySets) {
            if (data.get(key) != null) {
                return createRequestBody(data);
            }
            String value = String.valueOf(data.get(key));
            mData.addProperty(key, value);
        }
        String content = GsonUtils.toJson(mData);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
    }

    /**
     * 对象请求实体
     */
    private static <T> RequestBody createRequestBody(T data) {
        String content = GsonUtils.toJson(data);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
    }
}
