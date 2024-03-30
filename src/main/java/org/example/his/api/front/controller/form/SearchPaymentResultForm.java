package org.example.his.api.front.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SearchPaymentResultForm {
    @NotBlank(message = "outTradeNo不能为空")
    @Pattern(regexp = "^[0-9A-Z]{32}$", message = "outTradeNo内容不正确")
    private String outTradeNo;
}

