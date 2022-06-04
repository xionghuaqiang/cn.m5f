package cn.m5f.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhq
 * @date 2022/1/23 20:43
 */
@RestController
public class UserController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @GetMapping("admin/dt")
    public String admin(){
        return "admin";
    }
    @GetMapping("user/dt")
    public String user(){
        return "user";
    }
}
