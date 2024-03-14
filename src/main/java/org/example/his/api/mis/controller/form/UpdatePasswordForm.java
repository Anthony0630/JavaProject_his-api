package org.example.his.api.mis.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdatePasswordForm {
    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "password内容不正确")
    String password;

    @NotBlank(message = "newPassword")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",message = "newPassword内容不正确")
    private String newPassword;
}

