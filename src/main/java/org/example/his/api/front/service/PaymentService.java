package org.example.his.api.front.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface PaymentService {
    public ObjectNode unifiedOrder(String outTradeNo, int total,
                                   String desc, String notifyUrl,
                                   String timeExpire);
}

