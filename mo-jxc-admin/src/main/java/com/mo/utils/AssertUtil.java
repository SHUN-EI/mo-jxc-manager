package com.mo.utils;

import com.mo.enums.BizCodeEnum;
import com.mo.exception.BizException;

/**
 * Created by mo on 2022/11/2
 * 断言判断
 */
public class AssertUtil {

    public static void isTrue(Boolean flag, BizCodeEnum bizCodeEnum) {
        if (flag) {
            throw new BizException(bizCodeEnum);
        }
    }

    public static void isTrue(Boolean flag, Integer code, String msg) {
        if (flag) {
            throw new BizException(code, msg);
        }
    }
}
