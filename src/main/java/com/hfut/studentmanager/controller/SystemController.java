package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CourseService courseService;


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
                List<Map<String, Object>> teacherList = teacherService.listAllTeacher();
                return ResultUtils.success(teacherList);
            case "listAllExam":
                List<Map<String, Object>> examList = examService.listAllExam();
                return ResultUtils.success(examList);
            case "listAllClazz":
                List<Map<String, Object>> clazzList = clazzService.listAllClazz();
                return ResultUtils.success(clazzList);
            case "listAllGrade":
                List<Map<String, Object>> gradeList = gradeService.listAllGrade();
                return ResultUtils.success(gradeList);
            case "listAllCourse":
                List<Course> courseList = courseService.listAllCourse();
                return ResultUtils.success(courseList);
            default:
                return ResultUtils.error(404, "请求参数method错误");
        }
    }

    //todo 存在bug
    @Transactional
    @PostMapping("/add")
    public Message add(@RequestBody Map<String, Object> map){
        User user = new User();
        String method = (String) map.get("method");
        Message result;
        try {
            switch (method) {
                case "addStudent":
                    JSONObject jsonObjectStudent = JSONObject.fromObject(map.get("student"));
                    JSONStudent jsonStudent = (JSONStudent) JSONObject.toBean(jsonObjectStudent, JSONStudent.class);
                    user.setAccount(jsonStudent.getNumber());
                    user.setName(jsonStudent.getName());
                    user.setPassword("111111");
                    user.setType(2);
                    Message addStudentResult = studentService.addStudent(jsonStudent);
                    if (addStudentResult.getCode().equals("200")) {
                        Message addUserResult = userService.addUser(user);
                        if (addUserResult.getCode().equals("200")) {
                            return ResultUtils.success();
                        } else {
                            return addUserResult;
                        }
                    } else {
                        return addStudentResult;
                    }
                case "addTeacher":
                    //todo 有bug
                    JSONObject jsonObjectTeacher = JSONObject.fromObject(map.get("teacher"));
                    JSONTeacher jsonTeacher = (JSONTeacher) JSONObject.toBean(jsonObjectTeacher, JSONTeacher.class);

                    user.setAccount(jsonTeacher.getNumber());
                    user.setPassword("111111");
                    user.setName(jsonTeacher.getName());
                    user.setType(3);

                    Message addTeacherResult = teacherService.addTeacher(jsonTeacher);
                    if (addTeacherResult.getCode().equals("200")) {
                        Message addUserResult = userService.addUser(user);
                        if (addUserResult.getCode().equals("200")){
                            return ResultUtils.success();
                        }
                        return addUserResult;
                    } else {
                        return addTeacherResult;
                    }
                case "addCourse":
                    JSONObject jsonObjectCourse = JSONObject.fromObject(map.get("course"));
                    Course course = (Course) JSONObject.toBean(jsonObjectCourse, Course.class);
                    return courseService.addCourse(course);
                case "addGrade":
                    JSONObject jsonObjectGrade = JSONObject.fromObject(map.get("grade"));
                    JSONGrade jsonGrade = (JSONGrade) JSONObject.toBean(jsonObjectGrade, JSONGrade.class);
                    result = gradeService.addGrade(jsonGrade);
                    if (result.getCode().equals(404)){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }else {
                        return result;
                    }
                case "addClazz":
                    JSONObject jsonObjectClazz = JSONObject.fromObject(map.get("clazz"));
                    JSONClazz jsonClazz = (JSONClazz) JSONObject.toBean(jsonObjectClazz, JSONClazz.class);
                    result = clazzService.addClazz(jsonClazz);
                    if (result.getCode().equals(404)){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }else {
                        return result;
                    }
                case "addExam":
                    JSONObject jsonObjectExam = JSONObject.fromObject(map.get("exam"));
                    JSONExam jsonExam = (JSONExam) JSONObject.toBean(jsonObjectExam, JSONExam.class);
                    result = examService.addExam(jsonExam);
                    if (result.getCode().equals(404)){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }else {
                        return result;
                    }
                default:
                    return ResultUtils.error(404, "添加失败，method参数格式错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error(404, "添加失败");
        }
    }

    @GetMapping("/delete")
    public Message delete(@RequestParam("method") String method, @RequestParam("id") String id){
        switch (method){
            case "deleteTeacher":
                return teacherService.deleteTeacher(Integer.parseInt(id));

            default:
                return ResultUtils.error(404, "method格式错误");
        }
    }

    public Message loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultUtils.success();
    }

}
