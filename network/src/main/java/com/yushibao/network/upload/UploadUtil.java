package com.yushibao.network.upload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blankj.utilcode.util.ImageUtils;
import com.yushibao.employer.network.api.common.CommonApiEnum;
import com.yushibao.employer.network.framwork.Network;
import com.yushibao.employer.util.ListUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    public static List<UploadFormDataParams> requestImagePost(final List<String> imagePaths) {
        List<UploadFormDataParams> dataParamsList = new ArrayList<>();
        for (String imagePath : imagePaths) {
            File file = new File(imagePath);
            if (file.exists()) {
                dataParamsList.add(new UploadFormDataParams("headImg", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)));
            } else {
                return null;
            }
        }
        return dataParamsList;
    }

    /**
     * 组装 图片路径上传对象
     *
     * @param imagePath 图片路径
     */
    public static UploadFormDataParams requestImagePost(final String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            return new UploadFormDataParams("file", file.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
        } else {
            return null;
        }
    }

    /**
     * 执行上传
     *
     * @param dataParams file数据
     * @param callBack   回调
     */
    public static void upload(@CommonApiEnum String tag, @Nullable List<UploadFormDataParams> dataParams, NetworkUploadCallBack callBack) {
        if (ListUtils.isEmpty(dataParams)) {
            return;
        }
        switch (tag) {
            case CommonApiEnum.UPLOAD_HEAD_PIC:
                //上传头像
                Network.addObservable(Network.getInstance().getApi(UploadService.class).upLoadHeadPic(filesToMultipartBody(dataParams, callBack.getListener())),
                        callBack.getNetWorkSubscriber());
                break;
            default:
        }
    }

    /**
     * 上传图片，单张上传
     */
    public static void uploadPicSingle(String tag, int order, UploadFormDataParams dataParams, NetworkUploadCallBack callBack) {
        if (dataParams == null) {
            return;
        }
        callBack.setTag(tag);
        switch (tag) {
            case CommonApiEnum.UPLOAD_HEAD_PIC:
                //上传头像
                Network.addObservable(Network.getInstance().getApi(UploadService.class).upLoadHeadPic(filesToMultipartBody(order, dataParams, callBack.getListener())),
                        callBack.getNetWorkSubscriber());
                break;
            case CommonApiEnum.UPLOAD_PIC:
                //上传工作环境图片
                Network.addObservable(Network.getInstance().getApi(UploadService.class).uploadSingle(filesToMultipartBody(order, dataParams, callBack.getListener())),
                        callBack.getNetWorkSubscriber());
                break;
            default:
        }

    }


    /**
     * 生成 RequestBody
     *
     * @param params   file数据
     * @param listener 上传监听 主要是 监听 progress
     * @param order    排序数字 1，2，3，4，5
     * @return MultipartBody
     */
    private static MultipartBody filesToMultipartBody(int order, UploadFormDataParams params, UploadProgressListener listener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart(params.getName(), params.getValue(), new UploadProgressRequestBody(params.getBody(), listener));
        //上传多张图片
        if (order > 0) {
            builder.addFormDataPart("order", String.valueOf(order));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * 生成 RequestBody
     *
     * @param mDataParamsList file数据
     * @param listener        上传监听 主要是 监听 progress
     * @return MultipartBody
     */
    private static MultipartBody filesToMultipartBody(List<UploadFormDataParams> mDataParamsList, UploadProgressListener listener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (UploadFormDataParams params : mDataParamsList) {
            if (TextUtils.isEmpty(params.getName())) {
                continue;
            }
            if (params.getBody() == null) {
                builder.addFormDataPart(params.getName(), params.getValue());
                continue;
            }
            builder.addFormDataPart(params.getName(), params.getValue(), new UploadProgressRequestBody(params.getBody(), listener));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    private static final int MAX_WIDTH = 720;
    private static final int MAX_HEIGHT = 1280;

    /**
     * 返回指定大小的图片
     *
     * @param params 压缩图片参数
     * @return
     */
    public static void compressBitmap(final CompressImgParams params) {
        Observable.just(params)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CompressImgParams, Object>() {
                    @Override
                    public Object call(CompressImgParams params) {
                        Bitmap bitmap = BitmapFactory.decodeFile(params.getPath());
                        long bitmapSize = getBitmapSize(bitmap);
                        long size = params.getSize();
                        long tempSize = bitmapSize;
                        //     Log.i("compressBitmap", "原始大小：" + bitmapSize + " bitmap = " + bitmap + " size = " + params.getSize());
                        while (bitmapSize > size) {
                            float scale = 1f;
                            if (bitmap.getWidth() > bitmap.getHeight()) {
                                if (bitmap.getWidth() > MAX_WIDTH) {
                                    scale = MAX_WIDTH * 1f / bitmap.getWidth();
                                }
                            } else {
                                if (bitmap.getHeight() > MAX_HEIGHT) {
                                    scale = MAX_HEIGHT * 1f / bitmap.getHeight();
                                }
                            }
                            if (scale != 1) {
                                bitmap = ImageUtils.compressByScale(bitmap, scale, scale);
                            }
                            bitmapSize = getBitmapSize(bitmap);
                            //      Log.i("compressBitmap", "新图片大小：" + bitmapSize + " bitmap = " + bitmap + " size = " + params.getSize());
                            if (tempSize == bitmapSize) {
                                break;
                            } else {
                                tempSize = bitmapSize;
                            }
                        }
                        ImageUtils.save(bitmap, params.getPath() + "_compress", Bitmap.CompressFormat.JPEG);
                        return params.getPath() + "_compress";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        params.getCallback().onFail(e);
                    }

                    @Override
                    public void onNext(Object o) {
                        params.getCallback().onSucceed(o);
                    }
                });

    }

    /**
     * @param bitmap
     * @param quality 压缩质量大小
     * @return
     */
    private static Bitmap compressBitmap(Bitmap bitmap, int quality) {
        return ImageUtils.compressByQuality(bitmap, quality);
    }

    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray().length / 1024;
    }
}
