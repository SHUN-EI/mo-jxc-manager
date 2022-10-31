package com.mo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mo on 2022/10/31
 */
@RestController
@Slf4j
public class MainController {

    /**
     * 系统欢迎页
     *
     * @return
     */
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 系统主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main() {
        return "main";
    }

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
