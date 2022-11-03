package com.mo.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by mo on 2022/11/3
 */
@ApiModel(value = "用户更新请求对象")
@Data
public class UserUpdateRequest {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "备注名")
    private String bz;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;
}
