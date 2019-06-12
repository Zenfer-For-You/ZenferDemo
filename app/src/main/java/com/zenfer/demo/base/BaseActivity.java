package com.zenfer.demo.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.zenfer.demo.R;
import com.zenfer.demo.util.ActivityUtil;
import com.zenfer.demo.util.LeakCanaryUtil;
import com.zenfer.demo.util.eventbus.EventBusManager;
import com.zenfer.demo.widget.TitleBar;
import com.zenfer.demo.widget.emptyview.EmptyViewHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 *
 * @author Zenfer
 * @date 2019/6/11 18:02
 */
public abstract class BaseActivity extends AppCompatActivity implements TitleBar.OnBackButtonClickListener {

    /**
     * 内容布局
     */
    protected LinearLayout mRootView;
    /**
     * 内容布局
     */
    protected LinearLayout mContentView;
    /**
     * 自定义标题布局
     */
    public TitleBar mTitleBar;
    /**
     * 标题栏分割线
     */
    protected View mTitleDivider;
    /**
     * 情感图帮助类
     */
    public EmptyViewHelper mEmptyViewHelper;
    /**
     * ButterKnife实例
     */
    private Unbinder mButterKnife;

    protected View mTopView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        initParentWindowFeature();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_parent);
        initParentView();
        initRootData();
        initView(savedInstanceState, mContentView);
    }

    protected void initParentWindowFeature() {
    }

    /**
     * 初始化视图
     */
    private void initParentView() {
        initRootView();
        initRootTitle();
        initRootStatus();
    }

    /**
     * 初始化数据
     */
    public void initRootData() {
        if (enableEventBus()) {
            EventBusManager.register(this);
        }
        ActivityUtil.addActivity(this);
        mButterKnife = ButterKnife.bind(this);
    }

    /**
     * 初始化View
     */
    private void initRootView() {
        mRootView = findViewById(R.id.root_parent);
        mTopView = findViewById(R.id.top_view);
        mContentView = findViewById(R.id.root_container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(layoutId(), null);
        view.setClickable(true);
        mContentView.addView(view, layoutParams);
        // 初始化情感图
        mEmptyViewHelper = new EmptyViewHelper(this);
        mEmptyViewHelper.bind(mContentView);
    }

    /**
     * 初始化标题栏
     */
    private void initRootTitle() {
        // 获取标题栏视图
        mTitleBar = findViewById(R.id.title_bar);
        // 加载视图内容
        if (mTitleBar != null) {
            mTitleBar.setTitle(initTitleContent());
            mTitleBar.setOnBackButtonClickListener(this);
        }
        // 获取标题栏底部的分割线视图
        mTitleDivider = findViewById(R.id.title_divider_line);
    }

    /**
     * 初始化状态栏, Android  6.0以下手机不支持动态设置字体颜色为深色字体
     * <p>
     * (默认为字体暗色，背景白色)
     */
    public void initRootStatus() {
        //初始化状态栏，字体暗色，背景白色
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                // 设置状态栏背景颜色
                .statusBarColor(R.color.white)
                // 设置深色字体
                .statusBarDarkFont(true, 0.3f)
                // 兼容输入框遮挡问题
                .keyboardEnable(true)
                //初始化，默认透明状态栏和黑色导航栏
                .init();
    }

    /**
     * 显示和隐藏 TitleBar
     *
     * @param visible true 显示,反之隐藏
     */
    public void setTitleBarVisible(boolean visible) {
        mTitleBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置分割线是否显示
     *
     * @param isVisible
     */
    protected void setDividerVisible(boolean isVisible) {
        mTitleDivider.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackButtonClick() {
        finish();
    }

    @Override
    public void onDestroy() {
        // 销毁状态栏
        ImmersionBar.with(this).destroy();
        // 解除注解绑定
        if (mButterKnife != null) {
            mButterKnife.unbind();
            mButterKnife = null;
        }
        // EventBus解除绑定
        EventBusManager.unRegister(this);
        // Activity栈中移除当前活动
        ActivityUtil.removeActivity(this);
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.onDestroy();
        //LeakCanary 监听
        LeakCanaryUtil.watchActivity(this);
    }

    /**
     * 获取是否注册EventBus
     * 默认为False，处于关闭状态，如果需要则开启(true),子类重写该方法
     *
     * @return
     */
    protected boolean enableEventBus() {
        return false;
    }

    /**
     * 初始化布局（初始化业务视图）
     *
     * @param rootView
     */
    public abstract void initView(Bundle savedInstanceState, LinearLayout rootView);

    /**
     * 获取标题名称
     *
     * @return
     */
    protected abstract String initTitleContent();

    /**
     * 加载布局文件
     *
     * @return
     */
    protected abstract @LayoutRes
    int layoutId();
}
