package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_customer
 */
@Data
public class CustomerEntity implements Serializable {
    private Integer id;

    private String name;

    private String sex;

    private String tel;

    private String photo;

    private String createTime;

    private static final long serialVersionUID = 1L;
}