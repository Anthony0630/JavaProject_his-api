package org.example.his.api.mis.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SearchGoodsByPageForm {

    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{1,50}$", message = "keyword内容不正确")
    private String keyword;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "code内容不正确")
    private String code;

    @Pattern(regexp = "^父母体检$|^入职体检$|^职场白领$|^个人高端$|^中青年体检$", message = "type内容不正确")
    private String type;

    @Range(min = 1, max = 5, message = "partId范围不正确")
    private Byte partId;

    private Boolean status;

    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 10, max = 50, message = "length必须为10~50之间")
    private Integer length;

}

