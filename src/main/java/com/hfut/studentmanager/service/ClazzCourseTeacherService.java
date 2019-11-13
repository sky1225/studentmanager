package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ClazzCourseTeacherService {

    @Autowired
    ClazzCourseTeacherMapper clazzCourseTeacherMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    GradeMapper gradeMapper;

    public List<ClazzCourseTeacher> listClazzCourseTeacherByTeacherId(Integer teacherId){
        return clazzCourseTeacherMapper.findClazzCourseTeacherByTeacherId(teacherId);
    }

    @Transactional
    public Message addClazzCourseTeacher(ClazzCourseTeacher clazzCourseTeacher){
        if (teacherMapper.findTeacherById(clazzCourseTeacher.getTeacherId()) == null){
            return ResultUtils.error(404, "教师不存在");
        }
        if (courseMapper.findCourseById(clazzCourseTeacher.getCourseId()) == null){
            return ResultUtils.error(404, "该课程不存在");
        }
        if (clazzMapper.findClazzById(clazzCourseTeacher.getClazzId()) == null){
            return ResultUtils.error(404, "该班级不存在");
        }
        if (gradeMapper.findGradeById(clazzCourseTeacher.getGradeId()) == null){
            return ResultUtils.error(404, "该年级不存在");
        }
        if (clazzCourseTeacherMapper.findClazzCourseTeacherByClazzIdGradeIdCourseIdTeacherId(clazzCourseTeacher) != null){
            return ResultUtils.error(404, "该教师的该课程已存在");
        }
        if(!clazzCourseTeacherMapper.insertClazzCourseTeacher(clazzCourseTeacher)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "插入课程失败");
        }
        return ResultUtils.success();
    }

    @Transactional
    public Message deleteClazzCourseTeacher(Integer id){
        if (clazzCourseTeacherMapper.findClazzCourseTeacherById(id) == null){
            return ResultUtils.error(404, "教师所教课程不存在");
        }
        if (!clazzCourseTeacherMapper.deleteClazzCourseTeacher(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "该课程删除失败");
        }
        return ResultUtils.success();
    }
}
