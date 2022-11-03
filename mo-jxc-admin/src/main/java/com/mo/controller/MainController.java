package com.mo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mo on 2022/10/31
 */
@Controller
@Slf4j
public class MainController {

    /**
     * 系统欢迎页
     *
     * @return
     */
    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 系统主页面
     *
     * @return
     */
    @GetMapping("main")
    public String main() {
        return "main";
    }

    /**
     * 系统登录页
     *
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }
}
