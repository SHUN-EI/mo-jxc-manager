package com.mo.controller;


import com.mo.model.User;
import com.mo.service.IUserService;
import com.mo.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mo
 * @since 2022-11-02
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("用户登录")
    @RequestMapping("/login")
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession httpSession) {
        User user = userService.login(userName, password);
        httpSession.setAttribute("user", user);
        return RespBean.success("用户登录成功!", user);
    }

}
