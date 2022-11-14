package com.mo.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by mo on 2022/11/14
 * 验证码配置
 */
@ConfigurationProperties(prefix = "captcha")
@Configuration
@Data
public class CaptchaConfig {

    /**
     * 边框
     */
    private String border;
    /**
     * 边框颜色
     */
    private String borderColor;
    /**
     * 字体颜色
     */
    private String fontColor;
    /**
     * 图片宽度
     */
    private String imageWidth;
    /**
     * 图片高度
     */
    private String imageHeight;
    /**
     * 缓存key
     */
    private String sessionKey;
    /**
     * 字体名称
     */
    private String fontNames;
    /**
     * 字体大小
     */
    private String fontSize;
    /**
     * 验证码个数
     */
    private String charLength;


    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {

        DefaultKaptcha kaptcha = new DefaultKaptcha();

        //加载验证码配置
        Properties properties = new Properties();
        //边框
        properties.setProperty(Constants.KAPTCHA_BORDER, "no");
        //边框颜色
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, borderColor);
        //字体颜色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, fontColor);
        //图片宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, imageWidth);
        //图片高度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, imageHeight);
        //缓存key
        properties.setProperty(Constants.KAPTCHA_SESSION_KEY, sessionKey);
        //验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, charLength);
        //字体名称
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, fontNames);
        //字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, fontSize);
        kaptcha.setConfig(new Config(properties));
        return kaptcha;
    }


}
