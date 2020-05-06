package cn.m5f.springbootmyabtis.controller;

import cn.m5f.springbootmyabtis.entil.User;
import cn.m5f.springbootmyabtis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xhq
 * @date 2020/5/5 8:58
 */

@RestController
public class MyController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/hello")
    public List<User> selectUser(){
        return userMapper.getAllUser();
    }
}
