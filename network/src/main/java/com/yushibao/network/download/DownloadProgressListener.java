package com.yushibao.network.download;


import com.yushibao.employer.network.framwork.NetWordException;

import java.io.File;

/**
 * 下载监听
 *
 * @author Zenfer
 * @date 2019/6/14 9:38
 */
public interface DownloadProgressListener {
    /**
     * 下载成功
     *
     * @param file 文件
     */
    void onSuccess(File file);

    /**
     * 下载失败
     *
     * @param e 异常
     */
    void onFail(NetWordException e);

    /**
     * 下载开始
     */
    void onBegin();

    /**
     * 下载结束
     */
    void onEnd();

    /**
     * 下载进度
     *
     * @param currentBytesCount 当前文件下载大小
     * @param totalBytesCount   文件总大小
     * @param done              是否完成
     */
    void onProgress(long currentBytesCount, long totalBytesCount, boolean done);


}
