package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.GradeCourseMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.pojo.Course;
import com.hfut.studentmanager.pojo.Grade;
import com.hfut.studentmanager.pojo.GradeCourse;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private GradeCourseMapper gradeCourseMapper;
    @Autowired
    private CourseMapper courseMapper;


    public List<Map<String, Object>> listAllGrade() {
        List<Grade> gradeList = gradeMapper.findAllGrade();
        System.out.println(gradeList);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Grade grade: gradeList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", grade.getId());
            map.put("name", grade.getName());
            StringBuffer courseNames = new StringBuffer("| ");
            List<GradeCourse> gradeCourseList = gradeCourseMapper.findGradeCourseByGradeId(grade.getId());
            for (GradeCourse gradeCourse: gradeCourseList){
                courseNames.append(courseMapper.findNameById(gradeCourse.getCourseId()) + " | ");
            }
            map.put("courseNames", courseNames.toString());
            System.out.println(map);
            result.add(map);
        }
        System.out.println(result);
        return result;
    }

    public Message addGrade(JSONGrade jsonGrade){
        Integer gradeId = gradeMapper.findIdByName(jsonGrade.getName());
        if (gradeId != null){
            return ResultUtils.error(404, "该年级已存在");
        }
        String[] courses = jsonGrade.getCourses();
        Grade grade = new Grade();
        grade.setName(jsonGrade.getName());
        if (!gradeMapper.insertGrade(grade)){
            return ResultUtils.error(404, "年级添加失败");
        }
        gradeId = gradeMapper.findIdByName(jsonGrade.getName());
        for (String courseName: courses){
            Integer courseId;
            if ((courseId = courseMapper.findIdByName(courseName)) == null){
                Course course = new Course();
                course.setName(courseName);
                if (!courseMapper.insertCourse(course)){
                    return ResultUtils.error(404, "插入该年级对应课程'" + courseName + "'失败");
                }
                courseId = courseMapper.findIdByName(courseName);
            }
            GradeCourse gradeCourse = new GradeCourse();
            gradeCourse.setCourseId(courseId);
            gradeCourse.setGradeId(gradeId);
            if (!gradeCourseMapper.insertGradeCourse(gradeCourse)){
                return ResultUtils.error(404, "年级添加失败");
            }
        }
        return ResultUtils.success("年级添加成功");
    }

    public boolean deleteGrade(Integer id){
        return gradeMapper.deleteGradeById(id);
    }
}
