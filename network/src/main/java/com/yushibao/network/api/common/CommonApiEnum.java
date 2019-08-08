package com.yushibao.network.api.common;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        CommonApiEnum.GET_CODE_LOGIN,
        CommonApiEnum.LOGIN,
        CommonApiEnum.REGISTER,
        CommonApiEnum.CONFIG,
        CommonApiEnum.AUTH,
        CommonApiEnum.BANDCARD_CARD,
        CommonApiEnum.GET_MY_INFO,
        CommonApiEnum.UPLOAD_HEAD_PIC,
        CommonApiEnum.UPLOAD_PIC,
        CommonApiEnum.GET_BANNER,
        CommonApiEnum.CHANGE_NICKNAME,
        CommonApiEnum.CHANGE_PHONE,
        CommonApiEnum.PAY_ORDER,
        CommonApiEnum.COMPANY_AUTHENTICATION,  //企业认证
        CommonApiEnum.GET_COMPANY_INFO,  //获取企业信息
        CommonApiEnum.REVENUE_AND_EXPENDITURE,
        CommonApiEnum.WITHDRAW_LIST,
        CommonApiEnum.RECHARGE_LIST,
        CommonApiEnum.WITHDRAW_INFO,
        CommonApiEnum.WITHDRAW,
        CommonApiEnum.RECHARGE,
        CommonApiEnum.SET_PWD,
        CommonApiEnum.CHECK_USER,
        CommonApiEnum.CHECK_VERSION,
        CommonApiEnum.INVITE_LIST,
        CommonApiEnum.INVITE_DETAIL_LIST,
        CommonApiEnum.INVITE_SECOND_LIST,
        CommonApiEnum.INVITE_RQ,
})
@Retention(RetentionPolicy.SOURCE)
public @interface CommonApiEnum {

    /**
     * 获取验证码登录
     */
    String GET_CODE_LOGIN = "GET_CODE_LOGIN";
    /**
     * 去登录
     */
    String LOGIN = "LOGIN";
    /**
     * 查询未读消息数
     */
    String CONFIG = "CONFIG";
    /**
     * 实名认证
     */
    String AUTH = "AUTH";
    /**
     * 注册
     */
    String REGISTER = "REGISTER";
    /**
     * 注册
     */
    String BANDCARD_CARD = "BANDCARD_CARD";
    /**
     * 获取个人信息
     */
    String GET_MY_INFO = "GET_MY_INFO";
    /**
     * 上传头像
     */
    String UPLOAD_HEAD_PIC = "UPLOAD_HEAD_PIC";
    /**
     * 上传图片
     */
    String UPLOAD_PIC = "UPLOAD_PIC";
    /**
     * 获取Banner
     */
    String GET_BANNER = "GET_BANNER";
    /**
     * 修改昵称
     */
    String CHANGE_NICKNAME = "CHANGE_NICKNAME";

    /**
     * 支付和充值
     */
    String PAY_ORDER = "PAY_ORDER";
    /**
     * 更换手机号码
     */
    String CHANGE_PHONE = "CHANGE_PHONE";
    /**
     * 收支明细
     */
    String REVENUE_AND_EXPENDITURE = "REVENUE_AND_EXPENDITURE";
    /**
     * 企业认证
     */
    String COMPANY_AUTHENTICATION = "COMPANY_AUTHENTICATION";
    /**
     * 获取企业信息
     */
    String GET_COMPANY_INFO = "GET_COMPANY_INFO";
    /**
     * 提现记录
     */
    String WITHDRAW_LIST = "WITHDRAW_LIST";
    /**
     * 充值记录
     */
    String RECHARGE_LIST = "RECHARGE_LIST";
    /**
     * 提现费用获取
     */
    String WITHDRAW_INFO = "WITHDRAW_INFO";
    /**
     * 提现
     */
    String WITHDRAW = "WITHDRAW";
    /**
     * 充值
     */
    String RECHARGE = "RECHARGE";
    /**
     * 设置密码
     */
    String SET_PWD = "SET_PWD";
    /**
     * 身份验证
     */
    String CHECK_USER = "CHECK_USER";
    /**
     * 检测更新
     */
    String CHECK_VERSION = "CHECK_VERSION";
    /**
     * 已邀好友
     */
    String INVITE_LIST = "INVITE_LIST";
    /**
     * 邀请有礼 - 查看详情
     */
    String INVITE_SECOND_LIST = "INVITE_SECOND_LIST";
    /**
     * 奖励明细
     */
    String INVITE_DETAIL_LIST = "INVITE_DETAIL_LIST";
    /**
     * 邀请页二维码
     */
    String INVITE_RQ= "INVITE_RQ";
}
