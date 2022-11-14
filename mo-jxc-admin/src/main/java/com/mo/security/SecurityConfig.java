package com.mo.security;

import com.mo.filters.CaptchaCodeFilter;
import com.mo.model.User;
import com.mo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mo on 2022/11/7
 * Spring-Security 的配置
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JxcAuthenticationSuccessHandler jxcAuthenticationSuccessHandler;
    @Autowired
    private JxcAuthenticationFailedHandler jxcAuthenticationFailedHandler;
    @Autowired
    private JxcLogoutSuccessHandler jxcLogoutSuccessHandler;
    @Autowired
    private IUserService userService;
    @Autowired
    private CaptchaCodeFilter captchaCodeFilter;


    /**
     * 密码加密-单例模式
     *
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Spring-Security 认证使用的userDetailsService
     * 告诉 Spring-Security 使用的是 自己实现的 userDetailsService
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(encoder());
    }


    /**
     * Spring-Security 提供的登录服务
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService.findByUserName(username);
    }


    /**
     * Spring-Security 做系统登录相关配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable()
                //使用自定义的验证码校验过滤器,在登录操作前进行自定义的验证码校验
                .addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //允许iframe 页面嵌套
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                //系统的登录页
                .loginPage("/index")
                //Spring-Security 提供给的登录url
                .loginProcessingUrl("/login")
                .successHandler(jxcAuthenticationSuccessHandler)
                .failureHandler(jxcAuthenticationFailedHandler)
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(jxcLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                //放行登录页
                .antMatchers("/index", "/login", "/image").permitAll()
                //其他的请求都要验证
                .anyRequest().authenticated();

    }


    /**
     * 放行静态资源的配置
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/lib/**",
                        "/images/**",
                        "/error/**");
    }
}
