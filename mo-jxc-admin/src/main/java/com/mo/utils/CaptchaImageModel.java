package com.mo.utils;

import java.time.LocalDateTime;

/**
 * Created by mo on 2022/11/14
 * 验证码图片对象
 */
public class CaptchaImageModel {

    private String code;

    private LocalDateTime expireTime;


    public CaptchaImageModel(String code, int expireAfterSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }

    /**
     * 验证码是否失效
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
