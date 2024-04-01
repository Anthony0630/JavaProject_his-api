package org.example.his.api.mis.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class InsertRuleForm {
    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z\\u4e00-\\u9fa5]{1,20}$", message = "name内容不正确")
    private String name;

    @NotBlank(message = "rule不能为空")
    private String rule;

    private String remark;
}

