package org.example.his.api.front.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchIndexGoodsByPartForm {
    @NotEmpty(message = "partIds不能为空")
    private Integer[] partIds;
}

