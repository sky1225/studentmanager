package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EScoreService eScoreService;
    @Autowired
    private UserService userService;

    @PostMapping("/updatePasswordByStudent")
    public Message updatePasswordByStudent(@RequestParam("studentId") String studentId,
                                           @RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword){
        Student student = studentService.listStudentById(Integer.parseInt(studentId));
        return userService.updatePassword(student.getNumber(), oldPassword, newPassword);
    }

    @GetMapping("/listStudentByNumber")
    public Message listStudentById(@RequestParam("number") String number){
        Student student = studentService.listStudentByNumber(number);
        if (student != null){
            return ResultUtils.success(student);
        }
        return ResultUtils.error(404, "学生不存在");
    }

    @GetMapping("/listExamByStudent")
    public Message listExamByStudent(@RequestParam("studentId") String studentId){
        Student student = studentService.listStudentById(Integer.parseInt(studentId));
        Integer clazzId = student.getClazzId();
        Integer gradeId = student.getGradeId();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Exam exam: examService.listExamByGradeId(gradeId)){
            Map<String, Object> map = new HashMap<>();
            if (exam.getType().equals(1)  || exam.getClazzId().equals(clazzId)){
                map.put("id", exam.getId());
                map.put("name", exam.getName());
                map.put("time", exam.getTime());
                map.put("remark", exam.getRemark());
                map.put("type", exam.getType());
                map.put("grade", gradeService.listGradeById(exam.getGradeId()).getName());
                Clazz clazz = clazzService.listClazzById(exam.getClazzId());
                if (clazz == null){
                    map.put("clazz", "");
                }else {
                    map.put("clazz", clazz.getName());
                }
                map.put("course", courseService.listCourseById(exam.getCourseId()).getName());
                Escore escore = eScoreService.listEScoreByExamIdAndStudentId(exam.getId(), Integer.parseInt(studentId));
                if(escore == null){
                    map.put("score", "");
                }else {
                    map.put("score", escore.getScore().toString());
                }
                result.add(map);
            }
        }
        return ResultUtils.success(result);
    }

    @GetMapping("/listClazzByStudent")
    public Message listClazzByStudent(@RequestParam("studentId") String studentId){
        Student student = studentService.listStudentById(Integer.parseInt(studentId));
        if (student == null){
            return ResultUtils.error(404, "学生不存在");
        }
        Integer clazzId = student.getClazzId();
        List<Student> studentList = studentService.listStudentByClazz(clazzId);
        return ResultUtils.success(studentList);
    }
}
