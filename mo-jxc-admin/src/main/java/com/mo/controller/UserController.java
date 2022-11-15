package com.mo.controller;


import com.mo.enums.BizCodeEnum;
import com.mo.model.User;
import com.mo.request.UserQueryRequest;
import com.mo.request.UserUpdateRequest;
import com.mo.service.IUserService;
import com.mo.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

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


    @ApiOperation("用户列表查询")
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> userList(UserQueryRequest request) {
        return userService.userList(request);
    }

    @ApiOperation("用户管理主页")
    @GetMapping("index")
    public String index() {
        return "user/user";
    }


    @ApiOperation("用户密码更新")
    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(String oldPassword, String newPassword,
                                       String confirmPassword, Principal principal) {

        User updateUser = userService.updateUserPassword(principal.getName(), oldPassword, newPassword, confirmPassword);

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
    public String setting(Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName());
        //用户修改后数据变化，更新用户到会话中
        model.addAttribute("user", user);
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
     * 过期
     * 引入Spring-Security后不再使用此接口,转而使用Spring-Security提供的登录
     *
     * @param userName
     * @param password
     * @param httpSession
     * @return
     */
    public RespBean login(String userName, String password, HttpSession httpSession) {
        User user = userService.login(userName, password);
        httpSession.setAttribute("user", user);
        return null != user ? RespBean.success("用户登录成功!", user) :
                RespBean.buildResult(BizCodeEnum.USER_UNREGISTER);
    }

}
