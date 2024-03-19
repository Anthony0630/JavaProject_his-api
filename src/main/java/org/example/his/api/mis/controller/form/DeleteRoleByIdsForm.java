package org.example.his.api.mis.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DeleteRoleByIdsForm {

    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;
}

