package com.mo.controller;


import com.mo.enums.BizCodeEnum;
import com.mo.model.User;
import com.mo.request.UserUpdateRequest;
import com.mo.service.IUserService;
import com.mo.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @ApiOperation("用户密码更新")
    @RequestMapping("/updateUserPassword")
    public RespBean updateUserPassword(String oldPassword, String newPassword,
                                       String confirmPassword, HttpSession session) {

        User user = (User) session.getAttribute("user");
        User updateUser = userService.updateUserPassword(user.getUsername(), oldPassword, newPassword, confirmPassword);
        return null != updateUser ? RespBean.success("用户密码更新成功", updateUser) :
                RespBean.buildResult(BizCodeEnum.USER_UPDATEPWDERROR);

    }

    @ApiOperation("用户密码更新页")
    @RequestMapping("/password")
    public String password() {
        return "user/password";
    }

    @ApiOperation("用户信息设置页面")
    @RequestMapping("/setting")
    public String setting(HttpSession session) {
        User user = (User) session.getAttribute("user");
        //用户修改后数据变化，更新用户到会话中
        User updateUser = userService.getById(user.getId());
        session.setAttribute("user", updateUser);
        return "user/setting";

    }

    @ApiOperation("用户信息更新")
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(UserUpdateRequest request) {
        User user = userService.updateUserInfo(request);
        return null != user ? RespBean.success("用户信息更新成功", user) :
                RespBean.buildResult(BizCodeEnum.USER_UPDATEFAIL);

    }


    /**
     * 引入Spring-Security后不再使用此接口,转而使用Spring-Security提供的登录
     *
     * @param userName
     * @param password
     * @param httpSession
     * @return
     */
    @Deprecated
    @ApiOperation("用户登录")
    @RequestMapping("/login")
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession httpSession) {
        User user = userService.login(userName, password);
        httpSession.setAttribute("user", user);
        return null != user ? RespBean.success("用户登录成功!", user) :
                RespBean.buildResult(BizCodeEnum.USER_UNREGISTER);
    }

}
