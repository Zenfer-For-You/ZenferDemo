package com.zenfer.demo.network.framwork;

/**
 * <p>网络请求错误
 *
 * @author Zenfer
 */
public class NetWordException extends Exception {
    private int code;
    private String message;

    public NetWordException(java.lang.Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public NetWordException(java.lang.Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
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
}
