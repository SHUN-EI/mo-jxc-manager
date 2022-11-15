package com.mo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by mo on 2022/11/15
 */
@ApiModel("用户列表查询对象")
@Data
public class UserQueryRequest extends BaseQueryRequest {

    @ApiModelProperty("用户名")
    private String userName;
}
