package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.UserMapper;
import com.hfut.studentmanager.pojo.User;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Transactional
    public Message addUser(User user){
        if (userMapper.findUserByAccount(user.getAccount()) != null){
            return ResultUtils.error(404, "用户已存在");
        }
        if (!userMapper.insertUser(user)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "用户注册失败");
        }
        return ResultUtils.success("注册成功");
    }

    @Transactional
    public Message updatePassword(String account, String oldPassword, String newPassword){
        User user = userMapper.findUserByAccount(account);
        if (user == null){
            return ResultUtils.error(404, "用户不存在");
        }
        if (!user.getPassword().equals(oldPassword)){
            return ResultUtils.error(404, "密码错误");
        }
        user.setPassword(newPassword);
        if (!userMapper.updateUserPassword(user)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "密码修改失败");
        }
        return ResultUtils.success();
    }
}
