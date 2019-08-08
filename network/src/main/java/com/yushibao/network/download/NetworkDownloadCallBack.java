package com.yushibao.network.download;


import com.yushibao.employer.network.framwork.NetWordException;
import com.yushibao.employer.network.framwork.NetworkErrorHandler;

import java.io.File;

import rx.Subscriber;

import static com.yushibao.employer.network.common.NetworkErrorCode.ERROR_CODE_DOWNLOAD;

/**
 * 下载回调
 *
 * @author Zenfer
 * @date 2019/6/14 9:37
 */
public class NetworkDownloadCallBack {

    private DownloadProgressListener listener;

    public NetworkDownloadCallBack(DownloadProgressListener listener) {
        this.listener = listener;
    }

    public DownloadProgressListener getListener() {
        return listener;
    }

    public Subscriber<File> getNetWorkSubscriber() {
        return netWorkSubscriber;
    }

    private Subscriber netWorkSubscriber = new Subscriber<File>() {

        @Override
        public void onStart() {
            if (listener != null) {
                listener.onBegin();
            }
        }

        @Override
        public void onCompleted() {
            if (listener != null) {
                listener.onEnd();
            }
        }

        @Override
        public void onError(Throwable e) {
            try {
                if (listener != null) {
                    listener.onFail(NetworkErrorHandler.getException(e));
                    listener.onEnd();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        @Override
        public void onNext(File result) {
            if (result != null && result.exists() && result.getTotalSpace() > 0) {
                if (listener != null) {
                    listener.onSuccess(result);
                }
            } else {
                if (listener != null) {
                    listener.onFail(new NetWordException(null, ERROR_CODE_DOWNLOAD, "文件下载失败"));
                }
            }
        }
    };
}
