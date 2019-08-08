package com.yushibao.network.upload;

/**
 * @author gxm
 * @version V1.0
 * @date 2019/7/5 9:56
 * @Description: (描述)
 */
public class CompressImgParams {
    /**
     * 压缩图片的路径
     */
    String path;
    /**
     * 压缩大小 KB
     */
    int size = 200;
    /**
     * 回调
     */
    CompressCallback callback;


    public  CompressImgParams(String path, int size, CompressCallback callback) {
        this.path = path;
        this.size = size;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CompressCallback getCallback() {
        return callback;
    }

    public void setCallback(CompressCallback callback) {
        this.callback = callback;
    }
}
