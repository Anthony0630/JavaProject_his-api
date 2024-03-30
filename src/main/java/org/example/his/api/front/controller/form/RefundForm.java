package org.example.his.api.front.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RefundForm {
    private Integer customerId;
    @NotNull
    @Min(value = 1, message = "id不能小于1")
    private Integer id;
}

