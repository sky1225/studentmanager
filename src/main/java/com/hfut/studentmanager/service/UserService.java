package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.UserMapper;
import com.hfut.studentmanager.pojo.User;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String account, String password){
        User user = userMapper.findUserByAccount(account);
        if (user == null || !password.equals(user.getPassword())){
            return null;
        }
        return user;
    }

    public Message addUser(User user){
        if (userMapper.insertUser(user)){
            return ResultUtils.success("注册成功");
        }
        return ResultUtils.error(404, "用户注册失败");
    }
}
