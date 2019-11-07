package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.mapper.StudentMapper;
import com.hfut.studentmanager.pojo.Student;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONStudent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Student> listAllStudent(){
        return studentMapper.findAllStudent();
    }

    public Message addStudent(JSONStudent jsonStudent){
        Message message = new Message();
        Integer gradeId = gradeMapper.findIdByName(jsonStudent.getGrade());
        if (gradeId == null){
            return ResultUtils.error(404, "无此学生所在年级，添加学生信息失败");
        }
        List<Integer> clazzId = clazzMapper.findIdByNameAndGradeId(jsonStudent.getClazz(), gradeId);
        if (clazzId == null || clazzId.size() == 0){
            return ResultUtils.error(404, "无此学生所在年级下的班级，添加学生信息失败");
        }
        student.setNumber(jsonStudent.getNumber());
        student.setName(jsonStudent.getName());
        student.setQq(jsonStudent.getQq());
        student.setPhone(jsonStudent.getPhone());
        student.setSex(jsonStudent.getSex());
        student.setClazzId(clazzId.get(0));
        student.setGradeId(gradeId);
        System.out.println("student:" + student);
        if (studentMapper.insertStudent(student)){
            return ResultUtils.success("学生信息添加成功");
        }
        return ResultUtils.error(404, "此学生信息插入数据库失败");
    }
}
