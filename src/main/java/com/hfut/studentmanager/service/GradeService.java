package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.GradeCourseMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.pojo.Grade;
import com.hfut.studentmanager.pojo.GradeCourse;
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

    public boolean addGrade(Grade grade){
        return gradeMapper.insertGrade(grade);
    }

    public boolean deleteGrade(Integer id){
        return gradeMapper.deleteGradeById(id);
    }
}
