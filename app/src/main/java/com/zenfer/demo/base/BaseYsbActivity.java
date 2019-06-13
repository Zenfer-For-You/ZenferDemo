package com.zenfer.demo.base;

import android.os.Bundle;
import android.view.View;

import com.zenfer.demo.util.ReflectInstance;
import com.zenfer.demo.widget.emptyview.OnEmptyViewClickListener;


/**
 * 余时保 activity 基类
 *
 * @author Zenfer
 * @date 2019/6/11 18:36
 */
public abstract class BaseYsbActivity<P extends BasePresenter> extends BaseActivity implements OnEmptyViewClickListener {
    /**
     * 用于解决持久类中View实例化之后无法被清除，从而导致内存泄露
     */
    private P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = ReflectInstance.newTypeInstance(getClass(),0,this);
        super.onCreate(savedInstanceState);
    }

//    /**
//     * 创建持久类
//     */
//    public <T> T createPresenter(Class<T> clazz) {
//        try {
//            Constructor constructor = clazz.getConstructor(IBaseView.class);
//            mPresenter = (BasePresenter) constructor.newInstance(this);
//            return (T) mPresenter;
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public P getPresenter() {
        return mPresenter;
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
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        super.onDestroy();
    }


}
