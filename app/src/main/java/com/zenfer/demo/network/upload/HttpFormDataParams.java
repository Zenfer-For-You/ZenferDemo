package com.zenfer.demo.network.upload;

import okhttp3.RequestBody;

/**
 * 用于多文件上传参数
 *
 * @author Zenfer
 * @date 2019/6/13 16:06
 */
public class HttpFormDataParams {
    /**
     * 字段名
     */
    private String name;
    /**
     * 参数 , 例如 文件的路径
     */
    private String value;
    /**
     * body , 例如 RequestBody.create(MediaType.parse("application/octet-stream"), file)
     */
    private RequestBody body;

    public HttpFormDataParams(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public HttpFormDataParams(String name, String value, RequestBody body) {
        this.name = name;
        this.value = value;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }
}
