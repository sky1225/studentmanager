package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.pojo.Student;
import com.hfut.studentmanager.pojo.Teacher;
import com.hfut.studentmanager.pojo.User;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Message listAll(HttpServletRequest request, @RequestParam("method") String method){
        if (method == null){
            return ResultUtils.error(404, "请求参数method为空");
        }
        switch (method){
            case "LoginOut":
                return loginOut(request);
            case "listAllStudent":
                List<Student> studentList = studentService.listAllStudent();
                return ResultUtils.success(studentList);
            case "listAllTeacher":
                List<Teacher> teacherList = teacherService.listAllTeacher();
                return ResultUtils.success(teacherList);
            case "listAllExam":
                List<Map<String, Object>> examList = examService.listAllExam();
                return ResultUtils.success(examList);
            case "listAllClazz":
                List<Clazz> classList = clazzService.getClazzList();
                return ResultUtils.success(classList);
            default:
                return ResultUtils.error(404, "请求参数method错误");
        }
    }

    @PostMapping("/add")
    public Message add(
            @RequestParam("method") String method,
            @RequestParam("data") Map<String, Object> data){
        switch (method){
            case "addStudent":
                Student student = new Student();
                student.setName((String) data.get("name"));
                student.setNumber((String) data.get("number"));
                student.setSex((String) data.get("sex"));
                student.setPhone((String) data.get("phone"));
                student.setQq((String) data.get("qq"));
                User user = new User();
                user.setAccount(student.getNumber());
                user.setPassword("111111");
                user.setType(2);
                if (studentService.addStudent(student) && userService.addUser(user)){
                    return ResultUtils.success();
                }else {
                    return ResultUtils.error(404, "学生信息添加失败");
                }
//            case "addTeacher":
            default:
                return ResultUtils.error(404, "添加失败，method参数格式错误");

        }
    }

    public Message loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultUtils.success();
    }

}
