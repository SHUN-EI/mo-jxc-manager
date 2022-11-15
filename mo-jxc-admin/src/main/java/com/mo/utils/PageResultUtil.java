package com.mo.utils;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mo on 2022/11/15
 * 分页返回结果封装
 */
public class PageResultUtil {

    public static Map<String, Object> getPageResult(Long total, List<?> records) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", total);
        resultMap.put("data", records);
        return resultMap;

    }
}
