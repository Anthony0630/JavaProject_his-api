package org.example.his.api.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_user
 */
@Data
public class UserEntity implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String openId;

    private String photo;

    private String name;

    private String sex;

    private String tel;

    private String email;

    private String hiredate;

    private String role;

    private Integer root;

    private Integer deptId;

    private Integer status;

    private String createTime;

    private static final long serialVersionUID = 1L;
}