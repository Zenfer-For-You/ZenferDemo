package com.zenfer.demo.network.upload;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zenfer.demo.network.framwork.Network;
import com.zenfer.demo.util.ListUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传工具类
 *
 * @author Zenfer
 * @date 2019/6/14 9:39
 */
public class UploadUtil {

    /**
     * 组装 图片路径上传对象
     *
     * @param imagePaths 图片路径集合
     */
    public static List<HttpFormDataParams> requestImagePost(final List<String> imagePaths) {
        List<HttpFormDataParams> dataParamsList = new ArrayList<>();
        for (String imagePath : imagePaths) {
            File file = new File(imagePath);
            if (file.exists()) {
                dataParamsList.add(new HttpFormDataParams("headImg", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)));
            } else {
                return null;
            }
        }
        return dataParamsList;
    }

    /**
     * 执行上传
     *
     * @param dataParams file数据
     * @param callBack   回调
     */
    public static void upload(@Nullable List<HttpFormDataParams> dataParams, NetworkUploadCallBack callBack) {
        if (ListUtils.isEmpty(dataParams)) {
            return;
        }
        Network.addObservable(Network.getInstance().getApi(UploadService.class).upload(filesToMultipartBody(dataParams, callBack.getListener())),
                callBack.getNetWorkSubscriber());
    }

    /**
     * 生成 RequestBody
     * @param mDataParamsList file数据
     * @param listener 上传监听 主要是 监听 progress
     * @return  MultipartBody
     */
    private static MultipartBody filesToMultipartBody(List<HttpFormDataParams> mDataParamsList, UploadProgressListener listener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (HttpFormDataParams params : mDataParamsList) {
            if (TextUtils.isEmpty(params.getName())) {
                continue;
            }
            if (params.getBody() == null) {
                builder.addFormDataPart(params.getName(), params.getValue());
                continue;
            }
            builder.addFormDataPart(params.getName(), params.getValue(), new ProgressRequestBody(params.getBody(), listener));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}
