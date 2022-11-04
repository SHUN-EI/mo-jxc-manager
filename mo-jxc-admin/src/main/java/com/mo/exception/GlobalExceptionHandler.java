package com.mo.exception;

import com.mo.utils.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mo on 2022/11/4
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespBean handle(Exception e) {

        //是否为自定义异常
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;

            log.info("[业务异常]{}", e);
            return RespBean.buildCodeAndMsg(bizException.getCode(), bizException.getMessage());
        }else {
            log.info("[系统异常]{}",e);
            return  RespBean.error("全局异常，未知错误");
        }

    }
}
