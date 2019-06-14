package com.zenfer.demo.presenter;

import android.os.Environment;

import com.zenfer.demo.base.BasePresenter;
import com.zenfer.demo.base.IBaseView;
import com.zenfer.demo.network.api.NetWorkRequest;
import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.NetWordResult;
import com.zenfer.demo.network.framwork.NetWorkCallBack;
import com.zenfer.demo.network.upload.HttpFormDataParams;
import com.zenfer.demo.network.upload.NetworkUploadCallBack;
import com.zenfer.demo.network.upload.UploadProgressListener;
import com.zenfer.demo.network.upload.UploadUtil;

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
        List<HttpFormDataParams> datas = UploadUtil.requestImagePost(img);
        UploadUtil.upload(datas, callBack);
    }

}
