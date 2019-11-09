package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.Exam;
import com.hfut.studentmanager.pojo.Grade;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private GradeCourseMapper gradeCourseMapper;

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

    public Message addExam(JSONExam jsonExam){
        Exam exam = new Exam();
        exam.setName(jsonExam.getName());
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(jsonExam.getTime());
            exam.setTime(date);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error(404, "日期格式不规范");
        }
        exam.setRemark(jsonExam.getRemark());
        exam.setType(jsonExam.getType());
        Integer gradeId = gradeMapper.findIdByName(jsonExam.getGrade());

        Integer courseId = courseMapper.findIdByName(jsonExam.getCourse());
        if (gradeCourseMapper.findGradeCourseByGradeIdAndCourseId(gradeId, courseId) == null){
            return ResultUtils.error(404, jsonExam.getGrade() + "无课程‘" + jsonExam.getCourse() + "'");
        }
        Integer clazzId;
        if (jsonExam.getType() == 2){
            clazzId = clazzMapper.findIdByNameAndGradeId(jsonExam.getClazz(), gradeId);
            exam.setClazzId(clazzId);
        }
        exam.setGradeId(gradeId);
        exam.setCourseId(courseId);
        exam.setCourseId(courseId);
        if (examMapper.insertExam(exam)){
            return ResultUtils.success();
        }
        return ResultUtils.error(404, "添加考试失败");
    }

    public boolean deleteExam(Integer id){
        return examMapper.deleteExamById(id);
    }
}
