package com.zenfer.demo.network.upload;


import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.NetWordResult;

/**
 * 上传监听
 *
 * @author Zenfer
 * @date 2019/6/14 9:38
 */
public interface UploadProgressListener {
    /**
     * 上传成功
     *
     * @param result 回调结果
     */
    void onSuccess(NetWordResult result);

    /**
     * 上传失败
     *
     * @param e 异常
     */
    void onFail(NetWordException e);

    /**
     * 上传开始
     */
    void onBegin();

    /**
     * 上传结束
     */
    void onEnd();

    /**
     * 上传进度
     *
     * @param currentBytesCount 当前文件上传大小
     * @param totalBytesCount   文件总大小
     */
    void onProgress(long currentBytesCount, long totalBytesCount);


}
