package com.mo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by mo on 2022/10/31
 */
@Controller
@Slf4j
public class MainController {

    @ApiOperation("用户退出")
    @RequestMapping("signout")
    public String signout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index";
    }

    @ApiOperation("系统欢迎页")
    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @ApiOperation("系统主页面")
    @GetMapping("main")
    public String main() {
        return "main";
    }

    @ApiOperation("系统登录页")
    @GetMapping("index")
    public String index() {
        return "index";
    }
}
