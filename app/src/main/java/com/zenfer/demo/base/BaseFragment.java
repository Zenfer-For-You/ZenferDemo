package com.zenfer.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zenfer.demo.R;
import com.zenfer.demo.util.eventbus.EventBusManager;
import com.zenfer.demo.util.eventbus.EventBusParams;
import com.zenfer.demo.widget.emptyview.EmptyViewHelper;
import com.zenfer.demo.widget.emptyview.OnEmptyViewClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Fragment的基类
 *
 * @author Zenfer
 * @date 2018/9/18 15:36
 */
public abstract class BaseFragment extends Fragment implements OnEmptyViewClickListener {

    /**
     * 根布局
     */
    private LinearLayout mRootLayout;
    /**
     * 主布局
     */
    private View mContentView;

    /**
     * ButterKnife实例
     */
    private Unbinder mButterKnife;
    /**
     * 情感图帮助类
     */
    public EmptyViewHelper mEmptyViewHelper;
    /**
     * 用于解决持久类中View实例化之后无法被清除，从而导致内存泄露
     */
    private BasePresenter mBasePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = View.inflate(getContext(), R.layout.common_base_fragment_layout, null);
        initView(mRootView);
        return mRootView;
    }

    private void initView(View view) {
        mRootLayout = view.findViewById(R.id.root_layout);
        initContent();
        mButterKnife = ButterKnife.bind(this, view);
        initFragmentView(view);
        if (getEventBusEnable()) {
            EventBusManager.register(this);
        }
    }

    private void initContent() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContentView = View.inflate(getContext(), layoutId(), null);
        mRootLayout.addView(mContentView, layoutParams);
        // 初始化情感图
        mEmptyViewHelper = new EmptyViewHelper(getContext());
        mEmptyViewHelper.bind(mRootLayout);
    }

    /**
     * 创建持久类
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public <T> T createPresenter(Class<T> clazz) {
        Constructor constructor = null;
        try {
            constructor = clazz.getConstructor(IBaseView.class);
            mBasePresenter = (BasePresenter) constructor.newInstance(this);
            return (T) mBasePresenter;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
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

    public void onSuccess(Object entry, String tag) {
        onEnd(tag);
        mEmptyViewHelper.hideEmptyView();
    }


    public void onFailure(String tag, int code, String error) {
        onEnd(tag);
    }

    /**
     * 显示进度条对话框
     */
    public void onBegin(String tag) {
    }


    /**
     * 隐藏进度条对话框
     */
    public void onEnd(String tag) {
    }

    /**
     * EventBus接收器
     *
     * @param result 数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusParams result) {
    }

    /**
     * 获取是否注册EventBus
     * 默认为False，处于关闭状态，如果需要则开启(true),子类重写该方法
     *
     * @return
     */
    protected boolean getEventBusEnable() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mButterKnife != null) {
            mButterKnife.unbind();
            mButterKnife = null;
        }
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
        onEnd("");
        EventBusManager.unRegister(this);
    }

    abstract protected int layoutId();

    abstract protected void initFragmentView(View view);

}
