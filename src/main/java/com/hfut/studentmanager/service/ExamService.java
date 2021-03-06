package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.Exam;
import com.hfut.studentmanager.pojo.Grade;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private EscoreMapper escoreMapper;

    public Exam listExamById(Integer id){
        return examMapper.findExamById(id);
    }

    public List<Exam> listExamByGradeId(Integer gradeId){
        return examMapper.findExamByGradeId(gradeId);
    }

    public List<Exam> listExamByCourseId(Integer courseId){
        return examMapper.findExamByCourseId(courseId);
    }

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

    @Transactional
    public Message addExam(Exam exam){
        if (!examMapper.insertExam(exam)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "添加考试失败");
        }
        return ResultUtils.success();
    }

    @Transactional
    public Message deleteExam(Integer id){
        Exam exam = examMapper.findExamById(id);
        if (exam == null){
            return ResultUtils.error(404, "要删除的考试不存在");
        }
        if (!(escoreMapper.findEscoreByExamId(id) == null || escoreMapper.findEscoreByExamId(id) != null && escoreMapper.findEscoreByExamId(id).size() == 0)){
            return ResultUtils.error(404, "要删除的考试下存在学生成绩，删除失败");
        }
        if (!examMapper.deleteExamById(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除考试失败");
        }
        return ResultUtils.success();
    }
}
