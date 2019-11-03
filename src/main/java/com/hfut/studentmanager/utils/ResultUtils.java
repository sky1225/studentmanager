package com.hfut.studentmanager.utils;

import com.hfut.studentmanager.pojo.User;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
    private ResultUtils() {
    }

    /**
     * 登录验证成功的返回信息
     * @param user 成功登录的用户
     * @return
     */
    public static Message loginSuccess(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", user.getType());
        map.put("account", user.getAccount());
        Message message = new Message();
        message.setMsg("登录成功");
        message.setCode("200");
        message.setData(map);
        return message;
    }

    public static Message success(Object object) {
        Message message = new Message();
        message.setCode("200");
        message.setMsg("请求成功");
        message.setData(object);
        return message;
    }

    public static Message success() {
        Message message = new Message();
        message.setCode("200");
        message.setMsg("请求成功");
        return message;
    }

    public static Message success(String string) {
        Message message = new Message();
        message.setCode("200");
        message.setMsg(string);
        return message;
    }

    public static Message error(Integer code, String resultMessage) {
        Message message = new Message();
        message.setCode(code.toString());
        message.setMsg(resultMessage);
        return message;
    }
}
