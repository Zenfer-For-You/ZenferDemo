package com.zenfer.demo.network.download;

import com.blankj.utilcode.util.FileIOUtils;
import com.zenfer.demo.network.framwork.NetWordException;
import com.zenfer.demo.network.framwork.Network;
import com.zenfer.demo.network.framwork.RxUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import okhttp3.ResponseBody;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.zenfer.demo.network.common.NetworkErrorCode.ERROR_CODE_DOWNLOAD;

/**
 * 下载工具类
 *
 * @author Zenfer
 * @date 2019/6/14 10:26
 */
public class DownloadUtil {

    public static void download(String url, String path, String filename, NetworkDownloadCallBack callBack) {

        final File file = new File(path, filename);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        RxUtils.getInstance().addSubscription(
                Network.getInstance().getApi(callBack.getListener()).download(url)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new Func1<ResponseBody, InputStream>() {
                            @Override
                            public InputStream call(ResponseBody responseBody) {
                                return responseBody.byteStream();
                            }
                        })
                        .map(new Func1<InputStream, File>() {
                            @Override
                            public File call(InputStream inputStream) {
                                boolean isFileWrited = FileIOUtils.writeFileFromIS(file, inputStream);
                                return isFileWrited ? file : null;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(callBack.getNetWorkSubscriber())
        );
    }
}
