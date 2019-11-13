package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.System;
import java.util.*;

@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private GradeCourseMapper gradeCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private ClazzCourseTeacherMapper clazzCourseTeacherMapper;
    @Autowired
    private StudentMapper studentMapper;


    public List<Map<String, Object>> listAllGrade() {
        List<Grade> gradeList = gradeMapper.findAllGrade();
        System.out.println(gradeList);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Grade grade: gradeList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", grade.getId());
            map.put("name", grade.getName());
            List<GradeCourse> gradeCourseList = gradeCourseMapper.findGradeCourseByGradeId(grade.getId());
            if (!(gradeCourseList == null || gradeCourseList != null && gradeCourseList.size() == 0)){
                StringBuffer courseNames = new StringBuffer("| ");
                for (GradeCourse gradeCourse: gradeCourseList){
                    courseNames.append(courseMapper.findNameById(gradeCourse.getCourseId()) + " | ");
                }
                map.put("courseNames", courseNames.toString());
            }else {
                map.put("courseNames", "");
            }
            System.out.println(map);
            result.add(map);
        }
        System.out.println(result);
        return result;
    }

    @Transactional
    public Message addGrade(Grade grade){
        if (gradeMapper.findIdByName(grade.getName()) != null){
            return ResultUtils.error(404, "该年级已存在");
        }
        if (!gradeMapper.insertGrade(grade)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "年级添加失败");
        }
        return ResultUtils.success();
    }

    /**
     * 根据年级id删除年级，若该年级还存在班级或学生，删除失败
     * @param id 年级id
     * @return 删除结果
     */
    @Transactional
    public Message deleteGrade(Integer id){
        Grade grade = gradeMapper.findGradeById(id);
        if (grade == null){
            return ResultUtils.error(404, "年级不存在");
        }
        if (!(clazzMapper.findClazzByGradeId(id) == null || clazzMapper.findClazzByGradeId(id) != null && clazzMapper.findClazzByGradeId(id).size() == 0)){
            return ResultUtils.error(404, "该年级下还存在班级，删除失败");
        }
        if (!(studentMapper.findStudentByGradeId(id) == null || studentMapper.findStudentByGradeId(id) != null && studentMapper.findStudentByGradeId(id).size() == 0)){
            return ResultUtils.error(404, "该年级下还存在学生，删除失败");
        }
        if (!(gradeCourseMapper.findGradeCourseByGradeId(id) == null || gradeCourseMapper.findGradeCourseByGradeId(id) != null && gradeCourseMapper.findGradeCourseByGradeId(id).size() == 0)){
            return ResultUtils.error(404, "该年级下还存在课程，删除失败");
        }
        if (!gradeMapper.deleteGradeById(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除年级失败");
        }
        return ResultUtils.success();
    }
}
