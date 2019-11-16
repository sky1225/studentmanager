package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
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
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EscoreMapper escoreMapper;

    public Student listStudentById(Integer id){
        return studentMapper.findStudentById(id);
    }

    public Student listStudentByNumber(String number){
        return studentMapper.findStudentByNumber(number);
    }

    public List<Student> listStudentByClazz(Integer clazzId){
        return studentMapper.findStudentByClazzId(clazzId);
    }

    public List<Map<String, Object>> listAllStudent(){
        List<Map<String, Object>> result = new ArrayList<>();
        for (Student student: studentMapper.findAllStudent()){
            Map<String, Object> map = new HashMap<>();
            map.put("id", student.getId());
            map.put("number", student.getNumber());
            map.put("name", student.getName());
            map.put("sex", student.getSex());
            map.put("phone", student.getPhone());
            map.put("qq", student.getQq());
            map.put("clazzId", clazzMapper.findNameById(student.getClazzId()));
            map.put("gradeId", gradeMapper.findNameById(student.getGradeId()));
            result.add(map);
        }
        return result;
    }

    @Transactional
    public Message addStudent(Student student){
        if (studentMapper.findStudentByNumber(student.getNumber()) != null){
            return ResultUtils.error(404, "要添加学生的该学号已存在");
        }
        if (!studentMapper.insertStudent(student)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "此学生添加失败");
        }
        return ResultUtils.success("学生信息添加成功");
    }

    @Transactional
    public Message deleteStudent(Integer id){
        Student student = studentMapper.findStudentById(id);
        if (student == null){
            return ResultUtils.error(404, "此学生不存在，删除失败");
        }
        if (!(escoreMapper.findEscoreByStudentId(id) == null || escoreMapper.findEscoreByStudentId(id) != null && escoreMapper.findEscoreByStudentId(id).size() == 0)){
            if (!escoreMapper.deleteEscoreByStudentId(id)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.error(404, "删除失败");
            }
        }
        if (!userMapper.deleteUserByAccount(student.getNumber()) || !studentMapper.deleteStudentById(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除失败");
        }
        return ResultUtils.success();
    }
}
