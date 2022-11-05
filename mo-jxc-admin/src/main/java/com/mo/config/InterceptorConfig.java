package com.mo.config;

import com.mo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by mo on 2022/11/5
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //单例模式，项目启动初始化一个登录拦截器
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**") //拦截所有资源
                .excludePathPatterns("/index", "/user/login",
                        "/css/**", "/error/**",
                        "/images/**", "/js/**", "/lib/**"); //放行部分资源
    }
}
