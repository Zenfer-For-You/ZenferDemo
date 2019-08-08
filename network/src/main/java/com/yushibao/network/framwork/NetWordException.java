package com.yushibao.network.framwork;

/**
 * <p>网络请求错误
 *
 * @author Zenfer
 */
public class NetWordException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    public NetWordException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public NetWordException(Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
    }

    public NetWordException(Throwable throwable, int code, String message, Object data) {
        super(throwable);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
