package com.zenfer.demo.network.api;

import com.zenfer.demo.network.framwork.NetWordResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * retrofit api
 *
 * @author Zenfer
 * @date 2019/6/11 15:17
 */
public interface ApiService {

    @POST("/api/login/code")
    Observable<NetWordResult> codeLogin(@Body RequestBody body);

    @GET("/api/forget")
    Observable<NetWordResult> config(@QueryMap Map<String, String> map);

}
