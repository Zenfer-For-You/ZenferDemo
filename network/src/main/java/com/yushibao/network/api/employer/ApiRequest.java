package com.yushibao.network.api.employer;


import android.support.annotation.Nullable;

import com.yushibao.employer.bean.CompanyParamsBean;
import com.yushibao.employer.bean.ImageBean;
import com.yushibao.employer.network.framwork.NetWorkCallBack;
import com.yushibao.employer.network.framwork.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yushibao.employer.network.common.NetCommonParams.commonObjectParam;
import static com.yushibao.employer.network.common.NetCommonParams.commonParam;

/**
 * 网络请求方法
 *
 * @author Zenfer
 * @date 2019/6/11 16:10
 */
public class ApiRequest {
    /**
     * 补工资申请审核
     */
    public static void reissueSalaryDeal(int appeal_id, String status, String content, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("appeal_id", appeal_id);
        map.put("status", status);
        map.put("content", content);
        excute(map, ApiEnum.REISSUE_SALARY_DEAL, netWorkCallBack);
    }

    /**
     * 补工资申请列表
     */
    public static void reissueSalaryDetail(int appeal_id, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("appeal_id", appeal_id);
        excute(map, ApiEnum.REISSUE_SALARY_DETAIL, netWorkCallBack);
    }

    /**
     * 补工资申请列表
     */
    public static void reissueSalaryList(int page, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("page", page);
        excute(map, ApiEnum.REISSUE_SALARY_LIST, netWorkCallBack);
    }

    /**
     * 工资结算-全额结算
     */
    public static void settlementFull(int order_id, List<Integer> user_ids, int user_all, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("order_id", order_id);
        if (user_ids != null) {
            map.put("user_ids", user_ids);
        }
        if (user_all > 0) {
            map.put("user_all", user_all);
        }
        excute(map, ApiEnum.SETTLEMENT_FULL, netWorkCallBack);
    }

    /**
     * 订单结算-扣工资结算
     */
    public static void settlementDismiss(int order_id, int user_id, double duration, String remark, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("order_id", order_id);
        map.put("user_id", user_id);
        map.put("duration", duration);
        map.put("remark", remark);
        excute(map, ApiEnum.SETTLEMENT_DISMISS, netWorkCallBack);
    }

    /**
     * 订单结算-扣工资结算
     */
    public static void settlementBuckle(int order_id, int user_id, double duration, String remark, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("order_id", order_id);
        map.put("user_id", user_id);
        map.put("duration", duration);
        map.put("remark", remark);
        excute(map, ApiEnum.SETTLEMENT_BUCKLE, netWorkCallBack);
    }

    /**
     * 扣工资结算详情/解雇详情
     */
    public static void settlementInfo(int order_id, int user_id, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("order_id", order_id);
        map.put("user_id", user_id);
        excute(map, ApiEnum.SETTLEMENT_INFO, netWorkCallBack);
    }

    /**
     * 继续匹配
     */
    public static void keepMatching(int oid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("oid", oid);
        excute(map, ApiEnum.KEEP_MATCHING, netWorkCallBack);
    }

    /**
     * 子订单退款
     */
    public static void orderRefund(int oid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("oid", oid);
        excute(map, ApiEnum.ORDER_REFUND, netWorkCallBack);
    }

    /**
     * 取消订单
     */
    public static void orderCancel(int mid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("mid", mid);
        excute(map, ApiEnum.ORDER_CANCEL, netWorkCallBack);
    }

    /**
     * 长期工单个拒绝录用
     */
    public static void refuseEmployees(int mid, int uid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("mid", mid);
        map.put("uid", uid);
        excute(map, ApiEnum.REFUSE_EMPLOYEE, netWorkCallBack);
    }

    /**
     * 长期工批量录用
     */
    public static void offerEmployees(int mid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("mid", mid);
        excute(map, ApiEnum.OFFER_EMPLOYEES, netWorkCallBack);
    }

    /**
     * 员工列表
     */
    public static void getEmployeeList(int oid, int flag, int type, int page, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("oid", oid);
        map.put("flag", flag);
        map.put("type", type);
        map.put("page", page);
        excute(map, ApiEnum.EMPLOYEE_LIST, netWorkCallBack);
    }

    /**
     * 待支付订单 信息
     */
    public static void getPayOrderInfo(int mid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("mid", mid);
        excute(map, ApiEnum.PAY_ORDER_INFO, netWorkCallBack);
    }

    /**
     * 订单列表
     */
    public static void getOrderList(int status, int type, int page, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("status", status);
        map.put("type", type);
        map.put("page", page);
        excute(map, ApiEnum.ORDER_LIST, netWorkCallBack);
    }

    /**
     * 招工单位列表
     */
    public static void getCompanyList(NetWorkCallBack netWorkCallBack) {
        excute(commonParam(), ApiEnum.COMPANY_LIST, netWorkCallBack);
    }

    /**
     * 订单详情
     */
    public static void getOrderDetail(int mid, int type, int oid, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("mid", mid);
        map.put("type", type);
        if (oid > 0) {
            map.put("oid", oid);
        }
        excute(map, ApiEnum.ORDER_DETAIL, netWorkCallBack);
    }

    public static void deleteCompany(int id, NetWorkCallBack netWorkCallBack) {
        Map<String, String> map = commonParam();
        map.put("id", String.valueOf(id));
        excute(map, ApiEnum.COMPANY_DELETE, netWorkCallBack);
    }

    public static void saveInfo(CompanyParamsBean paramBean, NetWorkCallBack netWorkCallBack) {
        List<ImageBean> imageBeanList = paramBean.getImgs();
        List<Integer> imgs = new ArrayList<>();
        for (ImageBean bean : imageBeanList) {
            imgs.add(bean.getId());
        }
        Map<String, Object> map = commonObjectParam();
        map.put("name", paramBean.getName());
        map.put("province", paramBean.getProvince());
        map.put("city", paramBean.getCity());
        map.put("area", paramBean.getArea());
        map.put("location", paramBean.getLocation_address());
        map.put("linkman", paramBean.getLinkman());
        map.put("mobile", paramBean.getMobile());
        map.put("address", paramBean.getAddress());
        map.put("lat", paramBean.getLat());
        map.put("lon", paramBean.getLon());
        map.put("default", String.valueOf(paramBean.getIs_default()));
        map.put("imgs", imgs);
        int orderId = paramBean.getId();
        if (orderId > 0) {
            map.put("id", String.valueOf(orderId));
        }

        excute(map, ApiEnum.BUILD_NEW_ORDER_SAVE, netWorkCallBack);
    }

    public static void getOrderInfo(int id, NetWorkCallBack netWorkCallBack) {
        Map<String, String> map = commonParam();
        map.put("id", String.valueOf(id));
        excute(map, ApiEnum.EDIT_NEW_ORDER_INFO, netWorkCallBack);
    }

    /**
     * 获取配置信息
     *
     * @param aid 公司地址id
     */
    public static void getOrderConfig(int aid, NetWorkCallBack netWorkCallBack) {
        Map<String, String> map = commonParam();
        map.put("aid", String.valueOf(aid));
        excute(map, ApiEnum.ORDER_CONFIG, netWorkCallBack);
    }

    /**
     * 提交"我要招工"订单
     */
    public static void commitOrder(Map<String, Object> params, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.putAll(params);
        excute(map, ApiEnum.ORDER_COMMIT, netWorkCallBack);
    }

    /**
     * 地图招工
     */
    public static void mapMarker(double lat, double lon, NetWorkCallBack netWorkCallBack) {
        Map<String, Object> map = commonObjectParam();
        map.put("lat", lat);
        map.put("lon", lon);
        excute(map, ApiEnum.MAP_MARKER, netWorkCallBack);
    }


    public static void excute(@Nullable Object params, @ApiEnum String tag, NetWorkCallBack netWorkCallBack) {
        netWorkCallBack.setTag(tag);
        try {
            Network.addObservable(Api.get(tag, params), netWorkCallBack.getNetWorkSubscriber());
        } catch (Exception e) {
            netWorkCallBack.getNetWorkSubscriber().onError(e);
        }
    }

}
