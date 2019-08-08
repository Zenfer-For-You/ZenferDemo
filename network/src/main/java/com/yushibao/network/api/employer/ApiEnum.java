package com.yushibao.network.api.employer;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        ApiEnum.COMPANY_LIST,
        ApiEnum.COMPANY_DELETE,
        ApiEnum.BUILD_NEW_ORDER_SAVE,
        ApiEnum.EDIT_NEW_ORDER_INFO,
        ApiEnum.UPLOAD_PIC,
        ApiEnum.ORDER_CONFIG,
        ApiEnum.ORDER_COMMIT,
        ApiEnum.MAP_MARKER,
        ApiEnum.ORDER_LIST,
        ApiEnum.ORDER_DETAIL,
        ApiEnum.PAY_ORDER_INFO,
        ApiEnum.EMPLOYEE_LIST,
        ApiEnum.OFFER_EMPLOYEES,
        ApiEnum.REFUSE_EMPLOYEE,
        ApiEnum.ORDER_CANCEL,
        ApiEnum.ORDER_REFUND,
        ApiEnum.KEEP_MATCHING,
        ApiEnum.SETTLEMENT_INFO,
        ApiEnum.SETTLEMENT_BUCKLE,
        ApiEnum.SETTLEMENT_DISMISS,
        ApiEnum.SETTLEMENT_FULL,
        ApiEnum.REISSUE_SALARY_LIST,
        ApiEnum.REISSUE_SALARY_DEAL,
        ApiEnum.REISSUE_SALARY_DETAIL,
})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiEnum {
    /**
     * 招工单位列表
     * <p>
     * /**
     * 上传图片
     */
    String COMPANY_LIST = "COMPANY_LIST";
    /**
     * 删除招工单位
     */
    String COMPANY_DELETE = "COMPANY_DELETE";
    /**
     * 新建招工订单 上传数据
     */
    String BUILD_NEW_ORDER_SAVE = "BUILD_NEW_ORDER_SAVE";
    /**
     * 编辑招工订单 获取订单数据
     */
    String EDIT_NEW_ORDER_INFO = "EDIT_NEW_ORDER_INFO";
    String UPLOAD_PIC = "UPLOAD_PIC";
    /**
     * 获取""我要招工""配置信息
     */
    String ORDER_CONFIG = "ORDER_CONFIG";
    /**
     * 提交"我要招工"订单
     */
    String ORDER_COMMIT = "ORDER_COMMIT";
    /**
     * 地图marker
     */
    String MAP_MARKER = "MAP_MARKER";
    /**
     * 订单列表
     */
    String ORDER_LIST = "ORDER_LIST";
    /**
     * 订单详情
     */
    String ORDER_DETAIL = "ORDER_DETAIL";
    /**
     * 待支付订单信息
     */
    String PAY_ORDER_INFO = "PAY_ORDER_INFO";
    /**
     * 员工列表
     */
    String EMPLOYEE_LIST = "EMPLOYEE_LIST";
    /**
     * 长期工批量录用
     */
    String OFFER_EMPLOYEES = "OFFER_EMPLOYEES";
    /**
     * 长期工单个拒绝录用
     */
    String REFUSE_EMPLOYEE = "REFUSE_EMPLOYEE";
    /**
     * 取消订单
     */
    String ORDER_CANCEL = "ORDER_CANCEL";
    /**
     * 子订单退款
     */
    String ORDER_REFUND = "ORDER_REFUND";
    /**
     * 继续匹配
     */
    String KEEP_MATCHING = "KEEP_MATCHING";
    /**
     * 扣工资结算详情/解雇详情
     */
    String SETTLEMENT_INFO = "SETTLEMENT_INFO";
    /**
     * 订单结算-扣工资结算
     */
    String SETTLEMENT_BUCKLE = "SETTLEMENT_BUCKLE";
    /**
     * 工资结算-全额结算
     */
    String SETTLEMENT_FULL = "SETTLEMENT_FULL";
    /**
     * 补工资申请列表
     */
    String REISSUE_SALARY_LIST = "REISSUE_SALARY_LIST";
    /**
     * 补工资申请详情
     */
    String REISSUE_SALARY_DETAIL = "REISSUE_SALARY_DETAIL";
    /**
     * 补工资申请审核
     */
    String REISSUE_SALARY_DEAL = "REISSUE_SALARY_DEAL";
    /**
     * 解雇结算
     */
    String SETTLEMENT_DISMISS = "SETTLEMENT_DISMISS";
}
