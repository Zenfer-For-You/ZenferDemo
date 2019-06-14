package com.zenfer.demo.network.common;

/**
 * 网络请求错误码
 *
 * @author Zenfer
 * @date 2019/6/13 10:58
 */
public interface NetworkErrorCode {
    /**
     * 服务器返回数据为空错误
     */
    int ERROR_CODE_NULL = -1000;
    /**
     * Token失效
     */
    int ERROR_CODE_TOKEN = -10000;
    /**
     * 服务器返回数据为空错误
     */
    int ERROR_CODE_FORMAT = 1 << 8;
    /**
     * 请求超时
     */
    int ERROR_CODE_TIMEOUT = (1 << 8) + 1;
    /**
     * 连接服务器错误
     */
    int ERROR_CODE_CONNECT = (1 << 8) + 2;
    /**
     * json解析失败
     */
    int ERROR_CODE_JSON = (1 << 8) + 3;
    /**
     * 下载失败
     */
    int ERROR_CODE_DOWNLOAD = (1 << 8) + 4;
}
