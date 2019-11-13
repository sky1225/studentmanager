package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import com.hfut.studentmanager.pojo.Course;
import com.hfut.studentmanager.pojo.Teacher;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClazzCourseTeacherMapper clazzCourseTeacherMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private GradeCourseMapper gradeCourseMapper;

    public List<Map<String, Object>> listAllTeacher(){
        List<Teacher> teacherList = teacherMapper.findAllTeacher();
        if (teacherList == null || teacherList.size() == 0){
            return null;
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Teacher teacher: teacherList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", teacher.getId());
            map.put("number", teacher.getNumber());
            map.put("name", teacher.getName());
            map.put("phone", teacher.getPhone());
            map.put("qq", teacher.getQq());
            map.put("sex", teacher.getSex());

            List<ClazzCourseTeacher> clazzCourseTeacherList = clazzCourseTeacherMapper.findClazzCourseTeacherByTeacherId(teacher.getId());
            StringBuffer courses = new StringBuffer();
            for (ClazzCourseTeacher clazzCourseTeacher: clazzCourseTeacherList){
                Integer courseId = clazzCourseTeacher.getCourseId();
                Integer gradeId = clazzCourseTeacher.getGradeId();
                Integer clazzId = clazzCourseTeacher.getClazzId();
                String courseName = courseMapper.findNameById(courseId);
                String gradeName = gradeMapper.findNameById(gradeId);
                String clazzName = clazzMapper.findNameById(clazzId);
                courses.append("[" + gradeName + " " + clazzName + " " + courseName + "]" + " ");
            }
            map.put("courses", courses);
            result.add(map);
        }
        return result;
    }


    @Transactional
    public Message addTeacher(Teacher teacher){
        if (teacherMapper.findIdByNumber(teacher.getNumber()) != null){
            return ResultUtils.error(404, "该教师工号已存在");
        }
        if (!teacherMapper.insertTeacher(teacher)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "插入教师失败");
        }
        return ResultUtils.success("插入教师成功");
    }

    @Transactional
    public Message deleteTeacher(Integer id){
        Teacher teacher = teacherMapper.findTeacherById(id);
        if(teacher == null){
            return ResultUtils.error(404, "要删除的教师不存在");
        }
        for (ClazzCourseTeacher clazzCourseTeacher: clazzCourseTeacherMapper.findClazzCourseTeacherByTeacherId(id)){
            if (!clazzCourseTeacherMapper.deleteClazzCourseTeacher(clazzCourseTeacher.getId())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.error(404, "删除教师所教课程'" + courseMapper.findNameById(clazzCourseTeacher.getCourseId()) + "'失败");
            }
        }
        if (userMapper.deleteUserByAccount(teacher.getNumber()) && teacherMapper.deleteTeacher(id)){
            return ResultUtils.success();
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultUtils.error(404, "删除失败");
    }
}
