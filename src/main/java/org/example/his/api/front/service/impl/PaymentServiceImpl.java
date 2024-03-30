package org.example.his.api.front.service.impl;

import cn.felord.payment.PayException;
import cn.felord.payment.wechat.v3.WechatApiProvider;
import cn.felord.payment.wechat.v3.WechatResponseEntity;
import cn.felord.payment.wechat.v3.model.*;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.example.his.api.front.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.OffsetDateTime;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private WechatApiProvider wechatApiProvider;

    @Override
    public ObjectNode unifiedOrder(String outTradeNo, int total,
                                   String desc, String notifyUrl,
                                   String timeExpire) {
        PayParams params = new PayParams();

        Amount amount = new Amount();
        //amount.setTotal(total);
        amount.setTotal(1);  //设置付款金额为1分钱
        params.setAmount(amount);

        params.setOutTradeNo(outTradeNo);
        params.setDescription(desc);
        params.setNotifyUrl(notifyUrl);

        //设置场景信息
        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setPayerClientIp("127.0.0.1"); //终端设备（浏览器）的IP地址
        params.setSceneInfo(sceneInfo);

        if (timeExpire != null) {
            params.setTimeExpire(OffsetDateTime.parse(timeExpire));
        }

        WechatResponseEntity<ObjectNode> response = wechatApiProvider.directPayApi("his-vue").nativePay(params);
        if (response.is2xxSuccessful()) {
            ObjectNode json = response.getBody();
            return json;
        } else {
            log.error("创建微信支付订单失败", response.getBody());
            throw new PayException("创建微信支付订单失败");
        }
    }

    @Override
    public String searchPaymentResult(String outTradeNo) {
        TransactionQueryParams params = new TransactionQueryParams();
        params.setTransactionIdOrOutTradeNo(outTradeNo);
        WechatResponseEntity<ObjectNode> entity = wechatApiProvider.directPayApi("his-vue").queryTransactionByOutTradeNo(params);
        if (!entity.is2xxSuccessful()) {
            log.error("查询付款失败", entity.getBody());
            return null;
        }
        ObjectNode body = entity.getBody();

        String status = body.get("trade_state").textValue();
        if ("SUCCESS".equals(status)) {
            String transactionId = body.get("transaction_id").textValue();
            return transactionId;
        }
        return null;
    }

    @Override
    public String refund(String transactionId, Integer refund, Integer total, String notifyUrl) {
        RefundParams params = new RefundParams();
        params.setTransactionId(transactionId);
        String outRefundNo = IdUtil.simpleUUID().toUpperCase(); //生成退款流水号
        params.setOutRefundNo(outRefundNo);
        params.setNotifyUrl(notifyUrl);

        RefundParams.RefundAmount amount = new RefundParams.RefundAmount();
        amount.setRefund(refund); //退款金额
        amount.setTotal(total); //订单金额
        amount.setCurrency("CNY");
        params.setAmount(amount);

        WechatResponseEntity<ObjectNode> entity = wechatApiProvider.directPayApi("his-vue").refund(params);
        if (!entity.is2xxSuccessful()) {
            log.error("退款失败", entity.getBody());
            return null;
        }
        ObjectNode body = entity.getBody();
        //判断退款中状态
        if ("PROCESSING".equals(body.get("status").textValue())) {
            return outRefundNo;
        }
        return null;
    }

    public String searchRefundResult(String outRefundNo) {
        WechatResponseEntity<ObjectNode> entity = wechatApiProvider.directPayApi("his-vue").queryRefundInfo(outRefundNo);
        if (!entity.is2xxSuccessful()) {
            log.error("查询退款失败", entity.getBody());
            return "FAIL";
        }
        ObjectNode body = entity.getBody();
        String status = body.get("status").textValue();
        if ("SUCCESS".equals(status)) {
            return "SUCCESS";
        } else if ("ABNORMAL".equals(status)) {
            return "ABNORMAL";
        }
        return "FAIL";
    }
}

