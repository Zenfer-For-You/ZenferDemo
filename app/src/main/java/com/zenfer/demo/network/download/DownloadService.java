package com.zenfer.demo.network.download;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 下载服务
 *
 * @author Zenfer
 * @date 2019/6/14 9:43
 */
public interface DownloadService {
    /**
     * 下载
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
