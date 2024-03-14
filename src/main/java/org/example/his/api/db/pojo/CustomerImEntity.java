package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_customer_im
 */
@Data
public class CustomerImEntity implements Serializable {
    private Integer id;

    private Integer customerId;

    private String loginTime;

    private static final long serialVersionUID = 1L;
}