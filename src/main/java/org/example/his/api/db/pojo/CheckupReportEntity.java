package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_checkup_report
 */
@Data
public class CheckupReportEntity implements Serializable {
    private Integer id;

    private Integer appointmentId;

    private String resultId;

    private Integer status;

    private String filePath;

    private String waybillCode;

    private String waybillDate;

    private String date;

    private String createTime;

    private static final long serialVersionUID = 1L;
}