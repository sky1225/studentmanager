package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.TeacherMapper;
import com.hfut.studentmanager.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> listAllTeacher(){
        return teacherMapper.findAllTeacher();
    }

    public boolean addTeacher(Teacher teacher){
        return teacherMapper.insertTeacher(teacher);
    }
}
