package com.zenfer.demo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.zenfer.demo.R;
import com.zenfer.demo.base.IBaseView;
import com.zenfer.demo.base.BaseYsbActivity;
import com.zenfer.demo.presenter.MainPresenter;
import com.zenfer.demo.util.ResourceUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseYsbActivity implements IBaseView {

    @BindView(R.id.tv_test)
    TextView mTvTest;

    private MainPresenter presenter;

    @Override
    public void initView(Bundle savedInstanceState, LinearLayout rootView) {
        presenter = createPresenter(MainPresenter.class);
    }

    @Override
    protected String initTitleContent() {
        return ResourceUtil.getString(R.string.app_name);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.tv_test})
    void onClick(View v) {
        presenter.request();
    }

    @Override
    public void onSuccess(String tag, Object entry) {
        ToastUtils.showShort(entry.toString());
    }

    @Override
    public void onFailure(String tag, int code, String error) {
        ToastUtils.showShort(error);
    }

}
