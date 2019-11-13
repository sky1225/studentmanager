package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzCourseTeacherMapper;
import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.mapper.StudentMapper;
import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONClazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private ClazzCourseTeacherMapper clazzCourseTeacherMapper;
    @Autowired
    private StudentMapper studentMapper;
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

    public List<Clazz> listClazzByGradeId(Integer gradeId){
        return clazzMapper.findClazzByGradeId(gradeId);
    }

    @Transactional
    public Message addClazz(Clazz clazz){
        if (clazzMapper.findIdByNameAndGradeId(clazz.getName(), clazz.getGradeId()) != null){
            return ResultUtils.error(404, "要添加的班级已存在");
        }
        if (!clazzMapper.insertClazz(clazz)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "插入班级失败");
        }
        return ResultUtils.success();
    }

    @Transactional
    public Message deleteClazz(Integer id){
        Clazz clazz = clazzMapper.findClazzById(id);
        if (clazz == null){
            return ResultUtils.error(404, "要删除的班级不存在");
        }
        if (!(studentMapper.findStudentByClazzId(id) == null
                || studentMapper.findStudentByClazzId(id) != null && studentMapper.findStudentByClazzId(id).size() == 0)){
            return ResultUtils.error(404, "要删除的班级下还存在学生，删除失败");
        }
        List<ClazzCourseTeacher> clazzCourseTeacherList = clazzCourseTeacherMapper.findClazzCourseTeacherByClazzId(id);
        for (ClazzCourseTeacher clazzCourseTeacher: clazzCourseTeacherList){
            if (!clazzCourseTeacherMapper.deleteClazzCourseTeacher(clazzCourseTeacher.getId())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.error(404, "删除班级对应课程失败");
            }
        }
        if (!clazzMapper.deleteClazz(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除班级失败");

        }
        return ResultUtils.success();
    }
}
