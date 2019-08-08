package com.yushibao.network.api;

import android.support.annotation.StringDef;

@StringDef({
        HostEnum.HOST_COMMON,                        // 公共业务
        HostEnum.HOST_EMPLOYER,                      //雇主业务
})
public @interface HostEnum {

    /**
     * 公共业务
     */
    String HOST_COMMON = "HOST_COMMON";
    /**
     * 雇主业务
     */
    String HOST_EMPLOYER = "HOST_EMPLOYER";
}