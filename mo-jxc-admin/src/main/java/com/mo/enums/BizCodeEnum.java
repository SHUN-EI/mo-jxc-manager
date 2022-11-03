package com.mo.enums;

import lombok.Getter;

/**
 * Created by mo on 2022/11/2
 * 枚举类，统一状态码和错误信息
 *
 * @Description 状态码定义约束，共6位数，前3位代表服务，后3位代表接口
 * 比如 商品服务210,购物车是220、用户服务230，403代表权限
 */
public enum BizCodeEnum {

    /**
     * 用户相关
     */
    USER_UNREGISTER(230002, "账号不存在"),
    USER_PWD_ERROR(230003, "账号或者密码错误"),
    USER_UNLOGIN(230004, "账号未登录"),
    USER_NAMEEMPTY(230005, "用户名不能为空!"),
    USER_PWDEMPTY(230005, "用户名不能为空!"),
    USER_NAMEISEXIST(230006, "用户名已经存在!"),
    USER_UPDATEFAIL(230007, "用户信息更新失败!!");

    @Getter
    private int code;
    @Getter
    private String msg;


    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    }
