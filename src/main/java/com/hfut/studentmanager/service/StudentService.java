package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.*;
import com.hfut.studentmanager.pojo.Student;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONStudent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Iterator;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private Student student;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EscoreMapper escoreMapper;

    public List<Student> listAllStudent(){
        return studentMapper.findAllStudent();
    }

    public Message addStudent(JSONStudent jsonStudent){
        Message message = new Message();
        Integer gradeId = gradeMapper.findIdByName(jsonStudent.getGrade());
        if (gradeId == null){
            return ResultUtils.error(404, "无此学生所在年级，添加学生信息失败");
        }
        System.out.println(jsonStudent.getClazz() + gradeId);
        Integer clazzId = clazzMapper.findIdByNameAndGradeId(jsonStudent.getClazz(), gradeId);
        if (clazzId == null){
            return ResultUtils.error(404, "无此学生所在年级下的班级，添加学生信息失败");
        }
        student.setNumber(jsonStudent.getNumber());
        student.setName(jsonStudent.getName());
        student.setQq(jsonStudent.getQq());
        student.setPhone(jsonStudent.getPhone());
        student.setSex(jsonStudent.getSex());
        student.setClazzId(clazzId);
        student.setGradeId(gradeId);
        System.out.println("student:" + student);
        if (studentMapper.insertStudent(student)){
            return ResultUtils.success("学生信息添加成功");
        }
        return ResultUtils.error(404, "此学生信息插入数据库失败");
    }

    @Transactional
    public Message deleteStudent(Integer id){
        Student student = studentMapper.findStudentById(id);
        if (student == null){
            return ResultUtils.error(404, "此学生不存在，删除失败");
        }
        if (!userMapper.deleteUserByAccount(student.getNumber())
            || !escoreMapper.deleteEscoreByStudentId(id)
                || !studentMapper.deleteStudentById(id)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "删除失败");
        }
        return ResultUtils.success();
    }
}
