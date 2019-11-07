package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.pojo.Course;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
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

    public Message addCourse(Course course){
        if (courseMapper.insertCourse(course)){
            return ResultUtils.success("添加课程成功");
        }
        return ResultUtils.error(404, "课程添加失败");
    }
}
