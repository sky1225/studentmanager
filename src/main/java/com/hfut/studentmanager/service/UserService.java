package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.UserMapper;
import com.hfut.studentmanager.pojo.User;
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
}
