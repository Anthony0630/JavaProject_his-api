package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;

import java.util.Map;

public interface OrderService {
    public PageUtils searchByPage(Map param);
    public int checkPaymentResult(String[] outTradeNoArray);
    public int deleteById(int id);
    public int updateRefundStatusById(int id);
}

