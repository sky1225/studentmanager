package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.EscoreMapper;
import com.hfut.studentmanager.mapper.StudentMapper;
import com.hfut.studentmanager.pojo.Escore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    public Escore listEScoreByExamIdAndStudentId(Integer examId, Integer studentId){
        return escoreMapper.findEscoreByExamIdAndStudentId(examId, studentId).get(0);
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
