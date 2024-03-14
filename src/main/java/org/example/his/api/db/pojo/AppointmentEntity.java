package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_appointment
 */
@Data
public class AppointmentEntity implements Serializable {
    private Integer id;

    private String uuid;

    private Integer orderId;

    private String date;

    private String name;

    private String sex;

    private String pid;

    private String birthday;

    private String tel;

    private String mailingAddress;

    private String company;

    private Integer status;

    private String checkinTime;

    private String createTime;

    private static final long serialVersionUID = 1L;
}