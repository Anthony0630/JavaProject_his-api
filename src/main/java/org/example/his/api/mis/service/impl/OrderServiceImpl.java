package org.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.db.dao.OrderDao;
import org.example.his.api.front.service.PaymentService;
import org.example.his.api.mis.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("MisOrderServiceImpl")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private PaymentService paymentService;

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = new ArrayList<>();
        long count = orderDao.searchCount(param);
        if (count > 0) {
            list = orderDao.searchByPage(param);
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);

        return pageUtils;
    }

    @Override
    @Transactional
    public int checkPaymentResult(String[] outTradeNoArray) {
        int i = 0;
        for (String outTradeNo : outTradeNoArray) {
            String transactionId = paymentService.searchPaymentResult(outTradeNo);
            if (transactionId != null) {
                int rows = orderDao.updatePayment(new HashMap() {{
                    put("outTradeNo", outTradeNo);
                    put("transactionId", transactionId);
                }});
                i += rows;
            }
        }
        return i;
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        int rows = orderDao.deleteById(id);
        return rows;
    }

    @Override
    @Transactional
    public int updateRefundStatusById(int id) {
        int rows = orderDao.updateRefundStatusById(id);
        return rows;
    }
}

