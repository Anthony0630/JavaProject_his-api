package org.example.his.api.mis.controller.form.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CheckupVo {
    @NotBlank(message = "体检项目不能为空")
    @Length(max = 50, message = "体检项目不能超过50个字符")
    private String title;

    @NotBlank(message = "体检内容不能为空")
    @Length(max = 500, message = "体检内容不能超过500个字符")
    private String content;
}

