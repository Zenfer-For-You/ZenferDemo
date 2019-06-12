package com.zenfer.demo.network.api;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        ApiEnum.CODE_LOGIN,                        // 验证码登录
        ApiEnum.CONFIG,                  //配置
})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiEnum {

    /**
     * 验证码登录
     */
    String CODE_LOGIN = "CODE_LOGIN";
    /**
     * 查询未读消息数
     */
    String CONFIG = "CONFIG";
}
