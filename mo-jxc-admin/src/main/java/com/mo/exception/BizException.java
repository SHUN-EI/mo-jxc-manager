package com.mo.exception;

import com.mo.enums.BizCodeEnum;
import lombok.Data;

/**
 * Created by mo on 2022/11/2
 * 自定义异常
 */
@Data
public class BizException extends RuntimeException {

    private Integer code;
    private String message;


    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
        this.code = bizCodeEnum.getCode();
        this.message = bizCodeEnum.getMsg();
    }


}
