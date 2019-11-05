package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public List<Grade> listAllGrade() {
        return gradeMapper.findAllGrade();
    }

    public boolean addGrade(Grade grade){
        return gradeMapper.insertGrade(grade);
    }

    public boolean deleteGrade(Integer id){
        return gradeMapper.deleteGradeById(id);
    }
}
