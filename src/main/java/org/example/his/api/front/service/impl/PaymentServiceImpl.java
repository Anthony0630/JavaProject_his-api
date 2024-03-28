package org.example.his.api.front.service.impl;

import cn.felord.payment.PayException;
import cn.felord.payment.wechat.v3.WechatApiProvider;
import cn.felord.payment.wechat.v3.WechatResponseEntity;
import cn.felord.payment.wechat.v3.model.Amount;
import cn.felord.payment.wechat.v3.model.PayParams;
import cn.felord.payment.wechat.v3.model.SceneInfo;
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
}

