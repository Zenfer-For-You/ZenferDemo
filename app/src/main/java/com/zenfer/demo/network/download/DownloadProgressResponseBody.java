package com.zenfer.demo.network.download;

import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import rx.Observable;
import rx.functions.Action1;

/**
 * 下载 requestBody
 *
 * @author Zenfer
 * @date 2019/6/14 9:58
 */
public class DownloadProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final DownloadProgressListener progressListener;
    private BufferedSource bufferedSource;

    public DownloadProgressResponseBody(ResponseBody responseBody, DownloadProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }


    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long currentTotalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                final long bytesRead = super.read(sink, byteCount);
                currentTotalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (progressListener != null) {
                    Observable.just(currentTotalBytesRead).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            progressListener.onProgress(currentTotalBytesRead, responseBody.contentLength(), bytesRead == -1);
                        }
                    });
                }
                return bytesRead;
            }
        };
    }

}
