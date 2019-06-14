package com.zenfer.demo.presenter;

import android.os.Environment;
import android.util.Log;

import com.zenfer.demo.base.BasePresenter;
import com.zenfer.demo.base.IBaseView;
import com.zenfer.demo.network.api.NetWorkRequest;
import com.zenfer.demo.network.download.DownloadProgressListener;
import com.zenfer.demo.network.download.DownloadUtil;
import com.zenfer.demo.network.download.NetworkDownloadCallBack;
import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.NetWordResult;
import com.zenfer.demo.network.framwork.NetWorkCallBack;
import com.zenfer.demo.network.upload.NetworkUploadCallBack;
import com.zenfer.demo.network.upload.UploadFormDataParams;
import com.zenfer.demo.network.upload.UploadProgressListener;
import com.zenfer.demo.network.upload.UploadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<IBaseView> {

    public MainPresenter(IBaseView view) {
        super(view);
    }

    public void request() {
        NetWorkRequest.codeLogin("15089526459", "1111", new NetWorkCallBack(new NetWorkCallBack.BaseCallBack() {
            @Override
            public void onSuccess(String tag, NetWordResult result) {
                mView.onSuccess(tag, result);
            }

            @Override
            public void onFail(String tag, NetWordException msg) {
                mView.onFailure(tag, msg.getCode(), msg.getMessage());
            }

            @Override
            public void onBegin(String tag) {
                mView.onBegin(tag);
            }

            @Override
            public void onEnd(String tag) {
                mView.onEnd(tag);
            }
        }));
    }

    public void upload() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/tencent/MicroMsg/WeiXin/wx_camera_1551605101832.jpg";
        List<String> img = new ArrayList<>();
        img.add(path);
        NetworkUploadCallBack callBack = new NetworkUploadCallBack(new UploadProgressListener() {
            @Override
            public void onSuccess(NetWordResult result) {
                mView.onSuccess("", result);
            }

            @Override
            public void onFail(NetWordException msg) {
                mView.onFailure("", msg.getCode(), msg.getMessage());
            }

            @Override
            public void onBegin() {
                mView.onBegin("");
            }

            @Override
            public void onEnd() {
                mView.onEnd("");
            }

            @Override
            public void onProgress(long currentBytesCount, long totalBytesCount) {

            }
        });
        List<UploadFormDataParams> datas = UploadUtil.requestImagePost(img);
        UploadUtil.upload(datas, callBack);
    }

    public void download() {
        String url = "http://gdown.baidu.com/data/wisegame/fb6556f84cce95cf/yingyongbao_7362130.apk";
        String path = Environment.getExternalStorageDirectory().getPath() + "/tencent/MicroMsg/WeiXin/";
        String filename = "yingyongbao_7362130.apk";
        NetworkDownloadCallBack callBack = new NetworkDownloadCallBack(new DownloadProgressListener() {
            @Override
            public void onSuccess(File file) {
                Log.d("Zenfer", "下载成功 file = " + file.toString());
                mView.onSuccess("", file);
            }

            @Override
            public void onFail(NetWordException msg) {
                Log.d("Zenfer", "下载失败 file = " + msg.getMessage());
                mView.onFailure("", msg.getCode(), msg.getMessage());
            }

            @Override
            public void onBegin() {
                mView.onBegin("");
            }

            @Override
            public void onEnd() {
                mView.onEnd("");
            }

            @Override
            public void onProgress(long currentBytesCount, long totalBytesCount, boolean done) {
                Log.d("Zenfer", "currentBytesCount = " + currentBytesCount + " totalBytesCount = " + totalBytesCount);
            }
        });
        DownloadUtil.download(url, path, filename, callBack);
    }

}
