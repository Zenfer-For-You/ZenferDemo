package com.yushibao.network.upload;


import com.yushibao.employer.network.api.Host;
import com.yushibao.employer.network.api.HostManager;
import com.yushibao.employer.network.framwork.NetWordResult;

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
@Host(host = HostManager.HostEnum.HOST_COMMON)
public interface UploadService {

    /**
     * 上传图片 单张
     */
    @POST("/v1/upload/single")
    Observable<NetWordResult> uploadSingle(@Body MultipartBody multipartBody);

    /**
     * 上传头像
     */
    @POST("/v1/user/profile")
    Observable<NetWordResult> upLoadHeadPic(@Body MultipartBody multipartBody);

}
