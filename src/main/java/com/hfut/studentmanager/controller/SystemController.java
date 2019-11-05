package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.JSONStudent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.System;
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
                List<Clazz> classList = clazzService.listAllClazz();
                return ResultUtils.success(classList);
            default:
                return ResultUtils.error(404, "请求参数method错误");
        }
    }

    @PostMapping("/add")
    public Message add(@RequestBody Map<String, Object> map){
        System.out.println(map.get("student"));
        User user = new User();
        String method = (String) map.get("method");
        switch (method){
            case "addStudent":
                JSONObject jsonObject = JSONObject.fromObject(map.get("student"));
                JSONStudent jsonStudent = (JSONStudent) JSONObject.toBean(jsonObject, JSONStudent.class);
                user = new User();
                user.setAccount(jsonStudent.getNumber());
                user.setPassword("111111");
                user.setType(2);
                Message addStudentResult = studentService.addStudent(jsonStudent);
                if (addStudentResult.getCode().equals("200")){
                    Message addUserResult = userService.addUser(user);
                    if (addUserResult.getCode().equals("200")){
                        return ResultUtils.success();
                    }else {
                        return addUserResult;
                    }
                }else {
                    return addStudentResult;
                }
            case "addTeacher":
                Teacher teacher = (Teacher) map.get("teacher");
                user.setAccount(teacher.getNumber());
                user.setPassword("111111");
                user.setType(3);
                if (teacherService.addTeacher(teacher)){

                    return ResultUtils.success();
                }else {
                    return ResultUtils.error(404, "教师信息添加失败");
                }
            default:
                return ResultUtils.error(404, "添加失败，method参数格式错误");

        }
    }

    public Message loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultUtils.success();
    }

}
