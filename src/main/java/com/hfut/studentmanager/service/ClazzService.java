package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private GradeMapper gradeMapper;

    public List<Map<String, Object>> listAllClazz(){
        List<Clazz> clazzList = clazzMapper.findAllClazz();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Clazz clazz: clazzList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", clazz.getId());
            map.put("name", clazz.getName());
            map.put("grade", gradeMapper.findNameById(clazz.getGradeId()));
            result.add(map);
        }
        return result;
    }

    public boolean addClazz(Clazz clazz){
        return clazzMapper.insertClazz(clazz);
    }

    public boolean deleteClazz(Integer id){
        return clazzMapper.deleteClazz(id);
    }
}
