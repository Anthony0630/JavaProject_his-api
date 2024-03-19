package org.example.his.api.mis.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class UpdateRoleForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;

    @NotBlank(message = "roleName不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}", message = "roleName内容不正确")
    private String roleName;

    @NotEmpty(message = "permissions不能为空")
    private Integer[] permissions;

    @Length(max = 20, message = "desc不能超过20个字符")
    private String desc;

    @NotNull(message = "changed不能为空")
    private Boolean changed;
}

