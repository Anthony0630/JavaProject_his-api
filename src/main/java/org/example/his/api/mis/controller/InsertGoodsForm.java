package org.example.his.api.mis.controller;

import lombok.Data;
import org.example.his.api.mis.controller.form.vo.CheckupVo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class InsertGoodsForm {
    @NotBlank(message = "code不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "code内容不正确")
    private String code;

    @NotBlank(message = "title不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,50}$", message = "title内容不正确")
    private String title;

    @NotBlank(message = "description不能为空")
    @Length(max = 200, message = "description不能超过200个字符")
    private String description;

    @Valid
    private ArrayList<CheckupVo> checkup_1;

    @Valid
    private ArrayList<CheckupVo> checkup_2;

    @Valid
    private ArrayList<CheckupVo> checkup_3;

    @Valid
    private ArrayList<CheckupVo> checkup_4;

    @NotBlank(message = "image不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z/\\.]{1,200}$", message = "image内容不正确")
    private String image;

    @NotNull(message = "initialPrice不能为空")
    @Min(value = 0, message = "initialPrice不能小于0")
    private BigDecimal initialPrice;

    @NotNull(message = "currentPrice不能为空")
    @Min(value = 0, message = "currentPrice不能小于0")
    private BigDecimal currentPrice;

    @NotBlank(message = "type不能为空")
    @Pattern(regexp = "^父母体检$|^入职体检$|^职场白领$|^个人高端$|^中青年体检$")
    private String type;

    private String[] tag;

    @Range(min = 1, max = 5, message = "partId范围不正确")
    private Integer partId;

    @Min(value = 1, message = "ruleId不能小于1")
    private Integer ruleId;

}

