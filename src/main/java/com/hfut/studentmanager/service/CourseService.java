package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> listAllCourse(){
        return courseMapper.findAllCourse();
    }
}
