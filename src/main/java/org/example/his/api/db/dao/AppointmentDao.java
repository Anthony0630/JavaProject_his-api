package org.example.his.api.db.dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Anthony
* @description 针对表【tb_appointment(体检预约表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.AppointmentEntity
*/
public interface AppointmentDao {
    public ArrayList<HashMap> searchByOrderId(int orderId);
}





