package com.zenfer.demo.base;

import android.view.View;

import com.zenfer.demo.widget.emptyview.OnEmptyViewClickListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 余时保 activity 基类
 *
 * @author Zenfer
 * @date 2019/6/11 18:36
 */
public abstract class BaseYsbActivity extends BaseActivity implements OnEmptyViewClickListener {
    /**
     * 用于解决持久类中View实例化之后无法被清除，从而导致内存泄露
     */
    private BasePresenter mBasePresenter;

    /**
     * 创建持久类
     */
    public <T> T createPresenter(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructor(IBaseView.class);
            mBasePresenter = (BasePresenter) constructor.newInstance(this);
            return (T) mBasePresenter;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示无内容情感图
     */
    public void showNotContentError(String content) {
        mEmptyViewHelper.showNODataView(content, this);
    }

    /**
     * 显示无内容情感图 (下划线文案)
     */
    public void showNotContentError(String content, String underLineText) {
        mEmptyViewHelper.showNODataView(content, null, underLineText, this);
    }

    /**
     * 显示连接失败情感图
     */
    public void showConnectionError() {
        mEmptyViewHelper.showConnectErrorView(this);
    }

    /**
     * 显示网络异常情感图
     */
    public void showNetWorkError() {
        mEmptyViewHelper.showNetworkErrorView(this);
    }

    @Override
    public void emptyViewClick(View view, int flag) {

    }

    public void onBegin(String tag) {

    }

    public void onSuccess(String tag, Object entry) {
        onEnd(tag);
        mEmptyViewHelper.hideEmptyView();
    }

    public void onFailure(String tag, int code, String error) {
        onEnd(tag);
    }

    public void onEnd(String tag) {

    }

    @Override
    public void onDestroy() {
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
        super.onDestroy();
    }


}
