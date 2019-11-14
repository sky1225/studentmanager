package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.System;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private ClazzCourseTeacherService clazzCourseTeacherService;
    @Autowired
    private GradeCourseService gradeCourseService;

    @PostMapping("updatePasswordByAccount")
    public Message updatePasswordByAccount(@RequestParam("account") String account,
                                           @RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword){
        return userService.updatePassword(account, oldPassword, newPassword);
    }

    @GetMapping("/list")
    public Message listAll(HttpServletRequest request, @RequestParam("method") String method,
                           @RequestParam(value = "gradeId", required = false) String gradeId){
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
            case "listClazzByGradeId":
                List<Clazz> clazzListByGrade = clazzService.listClazzByGradeId(Integer.parseInt(gradeId));
                return ResultUtils.success(clazzListByGrade);
            case "listCourseByGradeId":
                List<Map<String, Object>> gradeCourseList = gradeCourseService.listGradeCourseByGradeId(Integer.parseInt(gradeId));
                return ResultUtils.success(gradeCourseList);
            default:
                return ResultUtils.error(404, "请求参数method错误");
        }
    }

    @PostMapping("/addStudent")
    public Message addStudent(@RequestParam(value = "number", required = true) String number,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "sex", required = true) String sex,
                              @RequestParam(value = "phone", required = false) String phone,
                              @RequestParam(value = "qq", required = false) String qq,
                              @RequestParam(value = "clazzId", required = true) String clazzId,
                              @RequestParam(value = "gradeId", required = true) String gradeId){
        User user = new User();
        Student student = new Student(null, number, name, sex, phone, qq, Integer.parseInt(clazzId), Integer.parseInt(gradeId));
        user.setAccount(student.getNumber());
        user.setName(student.getName());
        user.setPassword("111111");
        user.setType(2);
        Message addUserResult = userService.addUser(user);
        if (!addUserResult.getCode().equals("200")) {
            return addUserResult;
        }
        Message addStudentResult = studentService.addStudent(student);
        if (!addStudentResult.getCode().equals("200")){
            return addStudentResult;
        }
        return ResultUtils.success();
    }

    @PostMapping("/addCourseByTeacher")
    public Message addCourseByTeacher(@RequestParam(value = "clazzId", required = true) String cId,
                                      @RequestParam(value = "gradeId", required = true) String gId,
                                      @RequestParam(value = "courseId", required = true) String csId,
                                      @RequestParam(value = "teacherId", required = true) String tId){
        Integer clazzId = Integer.parseInt(cId);
        Integer gradeId = Integer.parseInt(gId);
        Integer courseId = Integer.parseInt(csId);
        Integer teacherId = Integer.parseInt(tId);
        ClazzCourseTeacher clazzCourseTeacher = new ClazzCourseTeacher(null, clazzId, gradeId, courseId, teacherId);
        return clazzCourseTeacherService.addClazzCourseTeacher(clazzCourseTeacher);
    }

    @PostMapping("/addTeacher")
    public Message addTeacher(@RequestParam(value = "number", required = true) String number,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "sex", required = true) String sex,
                              @RequestParam(value = "phone", required = false) String phone,
                              @RequestParam(value = "qq", required = false) String qq){
        User user = new User();
        Teacher teacher = new Teacher(null, number, name, sex, phone, qq);
        user.setAccount(teacher.getNumber());
        user.setName(teacher.getName());
        user.setPassword("111111");
        user.setType(3);
        Message addUserResult = userService.addUser(user);
        if (!addUserResult.getCode().equals("200")) {
            return addUserResult;
        }
        Message addTeacherResult = teacherService.addTeacher(teacher);
        if (!addTeacherResult.getCode().equals("200")){
            return addTeacherResult;
        }
        return ResultUtils.success();
    }

    @PostMapping("/addCourse")
    public Message addCourse(@RequestParam(value = "name", required = true) String name){
        Course course = new Course(null, name);
        return courseService.addCourse(course);
    }

    @PostMapping("/addGrade")
    public Message addGrade(@RequestParam(value = "name") String name){
        Grade grade = new Grade(null, name);
        return gradeService.addGrade(grade);
    }

    @PostMapping("addCourseByGrade")
    public Message addCourseByGrade(@RequestParam(value = "gradeId", required = true) String gId,
                                    @RequestParam(value = "courseId", required = true) String cId){
        Integer gradeId = Integer.parseInt(gId);
        Integer courseId = Integer.parseInt(cId);
        GradeCourse gradeCourse = new GradeCourse(null, gradeId, courseId);
        return gradeCourseService.addGradeCourse(gradeCourse);
    }

    @PostMapping("addClazz")
    public Message addClazz(@RequestParam(value = "name", required = true) String name,
                            @RequestParam(value = "gradeId", required = true) String gId){
        Integer gradeId = Integer.parseInt(gId);
        Clazz clazz = new Clazz(null, name, gradeId);
        return clazzService.addClazz(clazz);
    }

    @PostMapping("addExam")
    public Message addExam(@RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "time", required = true) String time,
                           @RequestParam(value = "remark", required = false) String remark,
                           @RequestParam(value = "type", required = true) String type,
                           @RequestParam(value = "gradeId", required = true) String gId,
                           @RequestParam(value = "clazzId", required = true) String cId,
                           @RequestParam(value = "courseId", required = true) String csId){
        Date date;
        System.out.println(time);
        Integer gradeId = Integer.parseInt(gId);
        Integer clazzId = Integer.parseInt(cId);
        Integer courseId = Integer.parseInt(csId);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(time);
            Exam exam = new Exam(null, name, date, remark, Integer.parseInt(type), gradeId, clazzId, courseId);
            return examService.addExam(exam);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResultUtils.error(404, "数据格式错误");
        }
    }

    @GetMapping("/delete")
    public Message delete(@RequestParam("method") String method, @RequestParam("id") String id){
        switch (method){
            case "deleteTeacher":
                return teacherService.deleteTeacher(Integer.parseInt(id));
            case "deleteStudent":
                return studentService.deleteStudent(Integer.parseInt(id));
            case "deleteGrade":
                return gradeService.deleteGrade(Integer.parseInt(id));
            case "deleteClazz":
                return clazzService.deleteClazz(Integer.parseInt(id));
            case "deleteCourse":
                return courseService.deleteCourse(Integer.parseInt(id));
            case "deleteExam":
                return examService.deleteExam(Integer.parseInt(id));
            default:
                return ResultUtils.error(404, "method格式错误");

        }
    }

//    @GetMapping("/getSystemInfo")
//    public Message systemManager(){
//        return ResultUtils.success(systemService.listSystemInfo());
//    }
//
//    @PostMapping("/getSystemInfo")
//    public Message getSystemInfo(@RequestParam("schoolName")){
//
//    }

    private Message loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultUtils.success();
    }

}
