package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.SystemMapper;
import com.hfut.studentmanager.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    @Autowired
    private SystemMapper systemMapper;

    public System listSystemInfo(){
        return systemMapper.findSystem();
    }
//    public Message updateSystem()
}
