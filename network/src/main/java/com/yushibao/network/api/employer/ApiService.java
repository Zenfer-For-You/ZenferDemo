package com.yushibao.network.api.employer;


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
 * 雇主业务接口
 *
 * @author Zenfer
 * @date 2019/6/11 15:17
 */
@Host(host = HostManager.HostEnum.HOST_EMPLOYER)
public interface ApiService {
    /**
     * 补工资申请审核
     */
    @POST("/v1/appeal/deal-with")
    Observable<NetWordResult> dealReissueSalary(@Body RequestBody body);

    /**
     * 补工资申请详情
     */
    @GET("/v1/appeal/info")
    Observable<NetWordResult> reissueSalaryDetail(@QueryMap Map<String, Object> map);

    /**
     * 补工资申请列表
     */
    @GET("/v1/appeal/list")
    Observable<NetWordResult> reissueSalaryList(@QueryMap Map<String, Object> map);

    /**
     * 工资结算-全额结算
     */
    @POST("/v1/order/settlement-full")
    Observable<NetWordResult> settlementFull(@Body RequestBody body);

    /**
     * 订单结算-解雇结算
     */
    @POST("/v1/order/settlement-dismiss")
    Observable<NetWordResult> settlementDismiss(@Body RequestBody body);

    /**
     * 订单结算-扣工资结算
     */
    @POST("/v1/order/settlement-buckle")
    Observable<NetWordResult> settlementBuckle(@Body RequestBody body);

    /**
     * 扣工资结算详情/解雇详情
     */
    @GET("/v1/order/settlement-info")
    Observable<NetWordResult> settlementInfo(@QueryMap Map<String, Object> map);

    /**
     * 继续匹配
     */
    @POST("/v1/operation/goOnMatchIng")
    Observable<NetWordResult> keepMatching(@Body RequestBody body);

    /**
     * 子订单退款
     */
    @POST("/v1/order/refund")
    Observable<NetWordResult> orderRefund(@Body RequestBody body);

    /**
     * 取消订单
     */
    @POST("/v1/order/cancel")
    Observable<NetWordResult> orderCancel(@Body RequestBody body);

    /**
     * 长期工单个拒绝录用
     */
    @POST("/v1/order/hire-refuse")
    Observable<NetWordResult> refuseEmployee(@Body RequestBody body);

    /**
     * 长期工批量录用
     */
    @POST("/v1/order/hire-confirm")
    Observable<NetWordResult> offerEmployees(@Body RequestBody body);

    /**
     * 员工列表
     */
    @GET("/v1/order/staff")
    Observable<NetWordResult> getEmployeeList(@QueryMap Map<String, Object> map);

    /**
     * 支付信息
     */
    @GET("/v1/order/to-pay")
    Observable<NetWordResult> getPayOrderInfo(@QueryMap Map<String, Object> map);

    /**
     * 订单列表
     */
    @GET("/v1/order/list")
    Observable<NetWordResult> getOrderList(@QueryMap Map<String, Object> map);

    /**
     * 招工单位列表
     */
    @GET("/v1/company/index")
    Observable<NetWordResult> getCompanyList(@QueryMap Map<String, Object> map);

    /**
     * 订单详情
     */
    @GET("/v1/order/detail")
    Observable<NetWordResult> getOrderDetail(@QueryMap Map<String, Object> map);

    /**
     * 实名认证
     */
    @POST("/v1/company/delete")
    Observable<NetWordResult> deleteCompany(@Body RequestBody body);

    /**
     * 新增或编辑
     */
    @POST("/v1/company/store")
    Observable<NetWordResult> saveCompanyInfo(@Body RequestBody body);

    /**
     * 招工单位详情
     *
     * @param map
     * @return
     */
    @GET("/v1/company/show")
    Observable<NetWordResult> getOrderInfo(@QueryMap Map<String, Object> map);

    /**
     * 获取""我要招工""配置信息
     */
    @GET("/v1/order/config")
    Observable<NetWordResult> orderConfig(@QueryMap Map<String, Object> map);

    /**
     * 提交 "我要招工" 订单
     */
    @POST("/v1/order/publish")
    Observable<NetWordResult> commitOrder(@Body RequestBody body);

    /**
     * 地图marker标签
     */
    @GET("/v1/index/nearby")
    Observable<NetWordResult> mapMarker(@QueryMap Map<String, Object> map);


}
