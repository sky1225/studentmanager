package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.utils.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @RequestMapping("/listExam")
    public Message listExam(@RequestParam("id") String id){
        return null;
    }
}
