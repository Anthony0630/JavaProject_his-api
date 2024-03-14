package org.example.his.api.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_module
 */
@Data
public class ModuleEntity implements Serializable {
    private Integer id;

    private String moduleCode;

    private String moduleName;

    private static final long serialVersionUID = 1L;
}