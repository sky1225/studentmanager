package com.hfut.studentmanager.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClazzServiceTest {

    @Autowired
    private ClazzService clazzService;

    @Test
    public void getClazzList() {
        if (clazzService == null){
            System.out.println("空指针");
        }else {
            System.out.println(clazzService.listAllClazz());

        }
    }
}