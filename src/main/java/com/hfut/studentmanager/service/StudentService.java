package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.StudentMapper;
import com.hfut.studentmanager.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> listAllStudent(){
        return studentMapper.findAllStudent();
    }

    public boolean addStudent(Student student){

        return studentMapper.insertStudent(student);
    }
}
