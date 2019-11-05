package com.hfut.studentmanager;

import com.hfut.studentmanager.service.ClazzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentmanagerApplicationTests {

    @Test
    void contextLoads() {
    }

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
