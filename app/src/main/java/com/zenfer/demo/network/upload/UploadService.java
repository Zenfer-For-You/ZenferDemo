package com.zenfer.demo.network.upload;


import com.zenfer.demo.network.framwork.NetWordResult;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 上传服务
 *
 * @author Zenfer
 * @date 2019/6/14 9:39
 */
public interface UploadService {

    /**
     * 上传
     */
    @POST("/api/user/change-himg")
    Observable<NetWordResult> upload(@Body MultipartBody multipartBody);

}
