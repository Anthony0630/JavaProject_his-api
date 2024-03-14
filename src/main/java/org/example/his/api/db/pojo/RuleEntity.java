package org.example.his.api.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_rule
 */
@Data
public class RuleEntity implements Serializable {
    private Integer id;

    private String name;

    private String rule;

    private String remark;

    private static final long serialVersionUID = 1L;
}