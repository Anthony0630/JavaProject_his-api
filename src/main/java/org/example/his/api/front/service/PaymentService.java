package org.example.his.api.front.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface PaymentService {
    public ObjectNode unifiedOrder(String outTradeNo, int total,
                                   String desc, String notifyUrl,
                                   String timeExpire);
    public String searchPaymentResult(String outTradeNo);
    public String refund(String transactionId, Integer refund, Integer total, String notifyUrl);
    public String searchRefundResult(String outRefundNo);
}

