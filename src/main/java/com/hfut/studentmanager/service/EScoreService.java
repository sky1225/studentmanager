package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.EscoreMapper;
import com.hfut.studentmanager.mapper.StudentMapper;
import com.hfut.studentmanager.pojo.Escore;
import com.hfut.studentmanager.pojo.Student;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EScoreService {

    @Autowired
    private EscoreMapper escoreMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Transactional
    public Message addEScoreByExamIdAndStudentIdAndCourseId(Integer examId, Integer studentId, Integer courseId, Integer score){
        for (Escore escore: escoreMapper.findEscoreByExamIdAndStudentId(examId, studentId)){
            escoreMapper.deleteEscore(escore.getId());
        }
        Student student = studentMapper.findStudentById(studentId);
        if (student == null){
            return ResultUtils.error(404, "要添加成绩的学生不存在");
        }
        Escore escore = new Escore(null, examId, student.getClazzId(), student.getId(), student.getGradeId(), courseId, score);
        if (!escoreMapper.insertEscore(escore)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "学生‘" + student.getName() + "’成绩添加失败");
        }
        return ResultUtils.success();
    }

    public Escore listEScoreByExamIdAndStudentId(Integer examId, Integer studentId){
        List<Escore> escoreList = escoreMapper.findEscoreByExamIdAndStudentId(examId, studentId);
        if (escoreList == null || escoreList != null && escoreList.size() == 0){
            return null;
        }
        return escoreList.get(0);
    }

    public List<Map<String, Object>> listESCoreByExamIdAndClazzId(Integer examId, Integer clazzId){
        List<Escore> escoreList = escoreMapper.findEscoreByExamIdAndClazzId(examId, clazzId);
        List<Map<String, Object>> result = new ArrayList<>();
        if (escoreList == null || escoreList != null && escoreList.size() == 0){
            return result;
        }
        for (Escore escore: escoreList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", escore.getId());
            map.put("number", studentMapper.findStudentById(escore.getStudentId()).getNumber());
            map.put("student", studentMapper.findStudentById(escore.getStudentId()).getName());
            map.put("score", escore.getScore());
            result.add(map);
        }
        return result;
    }
}
