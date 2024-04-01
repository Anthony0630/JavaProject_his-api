package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.OrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author Anthony
* @description 针对表【tb_order(订单表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.OrderEntity
*/
public interface OrderDao {
    public HashMap searchFrontStatistic(int customerId);
    public boolean searchIllegalCountInDay(int customerId);
    public int closeOrder();
    public int insert(OrderEntity entity);
    public int updatePayment(Map param);
    public Integer searchCustomerId(String outTradeNo);
    public ArrayList<HashMap> searchFrontOrderByPage(Map param);
    public long searchFrontOrderCount(Map param);
    public String searchAlreadyRefund(int id);
    public HashMap searchRefundNeeded(Map param);
    public int updateOutRefundNo(Map param);
    public int updateRefundStatusByOutRefundNo(String outRefundNo);
    public ArrayList<HashMap> searchTimeoutRefund();
    public int updateRefundStatusById(int id);
    public int closeOrderById(Map param);
    public ArrayList<HashMap> searchByPage(Map param);
    public long searchCount(Map param);
    public int deleteById(int id);
    public Integer hasOwnSnapshot(Map param);
}





