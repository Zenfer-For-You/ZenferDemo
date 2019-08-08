package com.yushibao.network.api.common;


import com.yushibao.employer.network.api.Host;
import com.yushibao.employer.network.api.HostManager;
import com.yushibao.employer.network.framwork.NetWordResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * 公共业务接口
 *
 * @author Zenfer
 * @date 2019/6/11 15:17
 */
@Host(host = HostManager.HostEnum.HOST_COMMON)
public interface CommonApiService {
    /**
     * 提现费用获取
     */
    @GET("/v1/withdraw/fee")
    Observable<NetWordResult> getWithdrawInfo(@QueryMap Map<String, Object> map);

    /**
     * 提现
     */
    @POST("/v1/withdraw/create")
    Observable<NetWordResult> withdraw(@Body RequestBody body);

    /**
     * 充值
     */
    @POST("/v1/recharge/create")
    Observable<NetWordResult> recharge(@Body RequestBody body);

    /**
     * 提现记录
     */
    @GET("/v1/withdraw/log")
    Observable<NetWordResult> getWithdrawList(@QueryMap Map<String, Object> map);

    /**
     * 充值记录
     */
    @GET("/v1/recharge/log")
    Observable<NetWordResult> getRechargeList(@QueryMap Map<String, Object> map);

    /**
     * 收支明细
     */
    @GET("/v1/user/accountLog")
    Observable<NetWordResult> getRevenueAndExpenditureList(@QueryMap Map<String, Object> map);

    /**
     * 获取验证码登录
     */
    @POST("/v1/user/code")
    Observable<NetWordResult> getLoginCode(@Body RequestBody body);

    /**
     * 去登录
     */
    @POST("/v1/user/login")
    Observable<NetWordResult> login(@Body RequestBody body);

    /**
     * 注册
     */
    @POST("/v1/user/register")
    Observable<NetWordResult> register(@Body RequestBody body);

    /**
     * 忘记密码
     */
    @GET("/v1/api/forget")
    Observable<NetWordResult> config(@QueryMap Map<String, Object> map);

    /**
     * 实名认证
     */
    @POST("/v1/cert/person")
    Observable<NetWordResult> auth(@Body RequestBody body);

    /**
     * 绑定银行卡
     */
    @POST("/v1/user/bind")
    Observable<NetWordResult> bandCard(@Body RequestBody body);

    /**
     * 获取个人信息
     */
    @GET("/v1/user/info")
    Observable<NetWordResult> getUserInfo(@QueryMap Map<String, Object> map);

    /**
     * 获取Banner
     */
    @GET("/v1/banner/list")
    Observable<NetWordResult> getBannerList(@QueryMap Map<String, Object> map);


    /**
     * 修改昵称
     */
    @POST("/v1/user/profile")
    Observable<NetWordResult> changeNockname(@Body RequestBody body);

    /**
     * 修改手机号
     */
    @POST("/v1/user/phone")
    Observable<NetWordResult> changePhone(@Body RequestBody body);

    /**
     * 支付订单
     *
     * @param body
     * @return
     */
    @POST("/v1/pay/create")
    Observable<NetWordResult> payOrder(@Body RequestBody body);

    /**
     * •企业认证
     *
     * @param body
     * @return
     */
    @POST("/v1/cert/company")
    Observable<NetWordResult> companyAuthentication(@Body RequestBody body);

    /**
     * 获取企业信息
     */
    @GET("/v1/cert/companyInfo")
    Observable<NetWordResult> getCompanyInfo(@QueryMap Map<String, Object> map);

    /**
     * 设置密码
     */
    @POST("/v1/pay/setPwd")
    Observable<NetWordResult> setPwd(@Body RequestBody body);

    /**
     * 身份验证
     */
    @POST("/v1/cert/personCheck")
    Observable<NetWordResult> checkUser(@Body RequestBody body);

    /**
     * 检测更新
     */
    @GET("/v1/update")
    Observable<NetWordResult> checkUpgrade(@QueryMap Map<String, Object> map);

    /**
     * 已邀好友
     */
    @GET("/v1/invite/list")
    Observable<NetWordResult> getInviteList(@QueryMap Map<String, Object> map);
    /**
     * 奖励明细
     */
    @GET("/v1/invite/trade-log")
    Observable<NetWordResult> getInviteDetailList(@QueryMap Map<String, Object> map);

    /**
     * 邀请有礼 - 查看详情
     */
    @GET("/v1/invite/second-invite")
    Observable<NetWordResult> getInviteSecondList(@QueryMap Map<String, Object> map);
    /**
     * 邀请有礼 - 邀请页二维码
     */
    @GET("/v1/user/shareRQ")
    Observable<NetWordResult> getInviteRQ(@QueryMap Map<String, Object> map);
}
