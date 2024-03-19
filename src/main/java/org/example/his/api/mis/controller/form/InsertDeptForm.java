package org.example.his.api.mis.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class InsertDeptForm {

    @NotBlank(message = "deptName不能为空")
    private String deptName;

    @Pattern(regexp = "^1[1-9]\\d{9}$|^(0\\d{2,3}\\-){0,1}[1-9]\\d{6,7}$", message = "tel内容错误")
    private String tel;

    @Email(message = "email不正确")
    private String email;

    @Length(max = 20, message = "desc不能超过20个字符")
    private String desc;
}

