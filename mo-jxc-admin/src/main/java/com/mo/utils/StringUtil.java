package com.mo.utils;

/**
 * Created by mo on 2022/11/2
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否是空
     *
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str) {
        return null == str || "".equals(str.trim()) ? true : false;
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static Boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim()) ? true : false;
    }
}
