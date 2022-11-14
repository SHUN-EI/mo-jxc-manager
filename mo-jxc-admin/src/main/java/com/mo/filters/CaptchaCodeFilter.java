package com.mo.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo.utils.CaptchaImageModel;
import com.mo.utils.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by mo on 2022/11/14
 * 验证码过滤器-用于Spring-Security中登录前的验证码校验
 */
@Component
public class CaptchaCodeFilter extends OncePerRequestFilter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //只有在登录请求是才有验证码校验的过滤操作
        if (StringUtils.equals("/login", request.getRequestURI()) &&
                StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            // 校验登录验证码是否正确
            try {
                validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(
                        RespBean.error("验证码错误")));
                //异常的话直接返回，不直接过滤操作
                return;
            }
        }

        //校验通过，执行过滤
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验
     *
     * @param request
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        HttpSession session = request.getRequest().getSession();

        //获取请求中参数值
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "captchaCode");

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new SessionAuthenticationException("验证码不能为空!");
        }

        //获取session中的验证码
        CaptchaImageModel codeInSession = (CaptchaImageModel) session.getAttribute("captcha_key");

        if (Objects.isNull(codeInSession)) {
            throw new SessionAuthenticationException("验证码不存在!");
        }

        if (codeInSession.isExpired()) {
            throw new SessionAuthenticationException("验证码已过期!");
        }

        //取反
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new SessionAuthenticationException("验证码不匹配!");
        }

    }
}
