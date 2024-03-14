package org.example.his.api.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_role
 */
@Data
public class RoleEntity implements Serializable {
    private Integer id;

    private String roleName;

    private String permissions;

    private String desc;

    private String defaultPermissions;

    private Integer systemic;

    private static final long serialVersionUID = 1L;
}