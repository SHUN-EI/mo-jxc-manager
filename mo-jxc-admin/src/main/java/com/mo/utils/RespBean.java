package com.mo.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.mo.enums.BizCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mo on 2022/11/2
 * 通用返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    @ApiModelProperty(value = "响应状态码")
    private Integer code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "响应结果数据")
    private Object data;


    /**
     * 成功，不传入数据
     *
     * @return
     */
    public static RespBean success() {
        return new RespBean(200, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @return
     */
    public static RespBean success(String msg, Object data) {
        return new RespBean(200, msg, data);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg
     * @return
     */
    public static RespBean error(String msg) {
        return new RespBean(-1, msg, null);
    }

    /**
     * 自定义状态码和错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static RespBean buildCodeAndMsg(int code, String msg) {
        return new RespBean(code, msg, null);
    }

    /**
     * 传入枚举，返回信息
     *
     * @param bizCodeEnum
     * @return
     */
    public static RespBean buildResult(BizCodeEnum bizCodeEnum) {
        return RespBean.buildCodeAndMsg(bizCodeEnum.getCode(), bizCodeEnum.getMsg());
    }


    /**
     * 获取远程调用数据
     * 注意事项：
     * 支持多单词下划线专驼峰（序列化和反序列化）
     * <p>
     * 使用阿里 fastjson2
     *
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(data), typeReference);
    }


}
