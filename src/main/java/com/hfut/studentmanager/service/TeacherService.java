package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import com.hfut.studentmanager.pojo.Course;
import com.hfut.studentmanager.pojo.Teacher;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONCourse;
import com.hfut.studentmanager.utils.jsonBean.JSONTeacher;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
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
    public Message addTeacher(JSONTeacher jsonTeacher){
        if (teacherMapper.findIdByNumber(jsonTeacher.getNumber()) != null){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "该教师工号已存在");
        }
        Teacher teacher = new Teacher();
        teacher.setName(jsonTeacher.getName());
        teacher.setNumber(jsonTeacher.getNumber());
        teacher.setPhone(jsonTeacher.getPhone());
        teacher.setSex(jsonTeacher.getSex());
        teacher.setQq(jsonTeacher.getQq());
        if (!teacherMapper.insertTeacher(teacher)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "插入教师失败");
        }
        List<JSONCourse> courseList = jsonTeacher.getCourses();
        for (JSONCourse course: courseList){
            Integer gradeId = gradeMapper.findIdByName(course.getGrade());
            if (gradeId == null){
                return ResultUtils.error(404, "‘" + course.getGrade() + "’不存在");
            }
            Integer courseId = courseMapper.findIdByName(course.getCourse());
            if (courseId == null){
                return ResultUtils.error(404, "不存在课程‘" + course.getCourse() + "’");
            }
            if (gradeCourseMapper.findGradeCourseByGradeIdAndCourseId(gradeId, courseId) == null){
                return ResultUtils.error(404, course.getGrade() + "不存在课程‘" + course.getCourse() + "’");
            }
            Integer clazzId = clazzMapper.findIdByNameAndGradeId(course.getClazz(), gradeId);
            if (clazzId == null){
                return ResultUtils.error(404, "‘" + course.getGrade() + course.getClazz() + "’不存在");
            }
            Integer teacherId = teacherMapper.findIdByNumber(teacher.getNumber());
            ClazzCourseTeacher clazzCourseTeacher = new ClazzCourseTeacher();
            clazzCourseTeacher.setClazzId(clazzId);
            clazzCourseTeacher.setGradeId(gradeId);
            clazzCourseTeacher.setTeacherId(teacherId);
            clazzCourseTeacher.setCourseId(courseId);
            if (!clazzCourseTeacherMapper.insertClazzCourseTeacher(clazzCourseTeacher)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.error(404, "插入该教师对应课程‘" + course.getCourse() + "’失败");
            }
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
