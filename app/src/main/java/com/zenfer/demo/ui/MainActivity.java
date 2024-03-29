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

public class MainActivity extends BaseYsbActivity<MainPresenter> implements IBaseView {

    @BindView(R.id.tv_request)
    TextView mTvTest;


    @Override
    public void initView(Bundle savedInstanceState, LinearLayout rootView) {
    }

    @Override
    protected String initTitleContent() {
        return ResourceUtil.getString(R.string.app_name);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.tv_request, R.id.tv_upload, R.id.tv_download})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_request:
                getPresenter().request();
                break;
            case R.id.tv_upload:
                getPresenter().upload();
                break;
            case R.id.tv_download:
                getPresenter().download();
                break;
            default:
        }

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
