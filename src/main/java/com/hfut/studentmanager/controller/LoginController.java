package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.User;
import com.hfut.studentmanager.service.UserService;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String hello(){
        return "你好，访问成功";
    }

    @PostMapping("/login")
    public Message login(
            @RequestParam("account") String account,
            @RequestParam("password") String password
    ){
        User user = userService.login(account, password);
        if (user == null){
            return ResultUtils.error(404, "登录失败");
        }
        return ResultUtils.loginSuccess(user);
    }
}
