package com.hfut.studentmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
public class ClazzServiceTest {

    @Autowired
    private ClazzService clazzService;

    @Test
    public void getClazzList() {
        if (clazzService == null){
            System.out.println("空指针");
        }else {
            System.out.println(clazzService.getClazzList());

        }
    }
}