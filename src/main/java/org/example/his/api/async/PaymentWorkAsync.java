package org.example.his.api.async;

import lombok.extern.slf4j.Slf4j;
import org.example.his.api.db.dao.OrderDao;
import org.example.his.api.exception.HisException;
import org.example.his.api.front.service.PaymentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class PaymentWorkAsync {

    @Resource
    private OrderDao orderDao;

    @Resource
    private PaymentService paymentService;

    @Async("AsyncTaskExecutor") //找到线程池，该方法的执行会被线程池分配给空闲的线程
    @Transactional
    public void closeTimeoutRefund(int id, String outRefundNo) {
        //查询退款结果
        String result = paymentService.searchRefundResult(outRefundNo);
        if ("SUCCESS".equals(result)) {
            //更新订单状态为已退款
            int rows = orderDao.updateRefundStatusById(id);
            if (rows != 1) {
                throw new HisException("订单更新为已退款状态失败");
            }
        } else if ("ABNORMAL".equals(result)) {
            /*
             * 1.先判断是否给用户发送过退款失败短信
             * 2.如果没有发送过短信，就给用户发送退款失败短信
             */
        }
    }
}

