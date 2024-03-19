package org.example.his.api.mis.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class InsertRoleForm {

    @NotBlank(message = "roleName不能为空")
    private String roleName;

    @NotEmpty(message = "permissions不能为空")
    private Integer[] permissions;


    @Length(max = 20,message = "desc不能超过20个字符")
    private String desc;
}

