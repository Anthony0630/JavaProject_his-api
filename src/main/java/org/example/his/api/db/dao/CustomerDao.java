package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.CustomerEntity;

import java.util.HashMap;

/**
* @author Anthony
* @description 针对表【tb_customer(客户表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.CustomerEntity
*/
public interface CustomerDao {
    public Integer searchIdByTel(String tel);
    public void insert(CustomerEntity entity);
    public HashMap searchById(int id);
}





