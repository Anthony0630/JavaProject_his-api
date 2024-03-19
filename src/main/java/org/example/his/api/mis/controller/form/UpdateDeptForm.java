package org.example.his.api.mis.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class UpdateDeptForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;

    @NotBlank(message = "deptName不能为空")
    private String deptName;

    @Pattern(regexp = "^1\\d{10}$|^(0\\d{2,3}\\-){0,1}[1-9]\\d{6,7}$", message = "tel内容不正确")
    private String tel;

    @Email(message = "email内容不正确")
    private String email;

    @Length(max = 20, message = "desc不能超过20个字符")
    private String desc;

}

