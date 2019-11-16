package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.GradeCourseMapper;
import com.hfut.studentmanager.pojo.Course;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.apache.ibatis.cache.decorators.TransactionalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private GradeCourseMapper gradeCourseMapper;

    public List<Course> listAllCourse(){
        return courseMapper.findAllCourse();
    }

    public Course listCourseById(Integer id){
        return courseMapper.findCourseById(id);
    }

    @Transactional
    public Message addCourse(Course course){
        Course course1 = courseMapper.findCourseByName(course.getName());
        if ( course1 != null){
            System.out.println(course1);
            return ResultUtils.error(404, "课程已存在");
        }
        if (!courseMapper.insertCourse(course)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "课程添加失败");
        }
        return ResultUtils.success("添加课程成功");
    }

    @Transactional
    public Message deleteCourse(Integer id) {
        Course course = courseMapper.findCourseById(id);
        if (course == null) {
            return ResultUtils.error(404, "要删除的课程不存在");
        }
        if (!(gradeCourseMapper.findGradeCourseByCourseId(id) == null
                || gradeCourseMapper.findGradeCourseByCourseId(id) != null && gradeCourseMapper.findGradeCourseByCourseId(id).size() == 0)) {
            return ResultUtils.error(404, "有年级存在该课程，删除失败");
        }
        if (!courseMapper.deleteCourseById(id)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除课程失败");
        }
        return ResultUtils.success();
    }

}
