package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_appointment_restriction
 */
@Data
public class AppointmentRestrictionEntity implements Serializable {
    private Integer id;

    private String date;

    private Integer num1;

    private Integer num2;

    private Integer num3;

    private String remark;

    private String createTime;

    private static final long serialVersionUID = 1L;
}