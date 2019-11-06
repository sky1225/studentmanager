package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.ExamMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Exam;
import com.hfut.studentmanager.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private CourseMapper courseMapper;

    public List<Map<String, Object>> listAllExam(){
        List<Exam> examList = examMapper.findAllExam();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Exam exam: examList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", exam.getId());
            map.put("name", exam.getName());
            map.put("time", exam.getTime());
            map.put("remark", exam.getRemark());
            map.put("type", exam.getType());
            map.put("grade", gradeMapper.findNameById(exam.getGradeId()));
            map.put("clazz", clazzMapper.findNameById(exam.getClazzId()));
            map.put("course", courseMapper.findNameById(exam.getCourseId()));
            result.add(map);
        }
        return result;
    }

    public boolean addExam(Exam exam){
        return examMapper.insertExam(exam);
    }

    public boolean deleteExam(Integer id){
        return examMapper.deleteExamById(id);
    }
}
