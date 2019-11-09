package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONClazz;
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

    public Message addClazz(JSONClazz jsonClazz){
        Clazz clazz = new Clazz();
        clazz.setName(jsonClazz.getName());
        Integer gradeId = gradeMapper.findIdByName(jsonClazz.getGrade());
        if (gradeId == null){
            return ResultUtils.error(404, "不存在年级：" + jsonClazz.getGrade());
        }
        clazz.setGradeId(gradeId);
        if (clazzMapper.insertClazz(clazz)){
            return ResultUtils.success();
        }
        return ResultUtils.error(404, "插入班级失败");
    }

    public boolean deleteClazz(Integer id){
        return clazzMapper.deleteClazz(id);
    }
}
