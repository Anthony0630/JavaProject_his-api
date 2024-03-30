package org.example.his.api.front.service;

import org.example.his.api.common.PageUtils;

import java.util.HashMap;
import java.util.Map;

public interface OrderService {
    public HashMap createPayment(Map param);
    public boolean updatePayment(Map param);
    public Integer searchCustomerId(String outTradeNo);
    public boolean searchPaymentResult(String outTradeNo);
    public PageUtils searchByPage(Map param);
    public boolean refund(Map param);
    public boolean updateRefundStatus(String outRefundNo);
    public String payOrder(int customerId, String outTradeNo);
    public boolean closeOrderById(Map param);
}

