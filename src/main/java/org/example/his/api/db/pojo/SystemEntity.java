package org.example.his.api.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_system
 */
@Data
public class SystemEntity implements Serializable {
    private Integer id;

    private String item;

    private String value;

    private String remark;

    private static final long serialVersionUID = 1L;
}