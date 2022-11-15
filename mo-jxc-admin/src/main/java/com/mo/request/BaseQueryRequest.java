package com.mo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by mo on 2022/11/15
 */
@ApiModel("通用分页请求对象")
@Data
public class BaseQueryRequest {

    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页显示多少条")
    private Integer limit;
}
