package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.mapper.ClazzCourseTeacherMapper;
import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.Clazz;
import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import com.hfut.studentmanager.pojo.Exam;
import com.hfut.studentmanager.pojo.Student;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.sun.javafx.menu.MenuBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ClazzCourseTeacherService clazzCourseTeacherService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EScoreService eScoreService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/listExam")
    public Message listExam(@RequestParam("teacherId") String teacherId){
        List<ClazzCourseTeacher> clazzCourseTeacherList = clazzCourseTeacherService.listClazzCourseTeacherByTeacherId(Integer.parseInt(teacherId));
        List<Map<String, Object>> result = new ArrayList<>();

        for (ClazzCourseTeacher clazzCourseTeacher: clazzCourseTeacherList){
            Integer courseId = clazzCourseTeacher.getCourseId();
            Integer clazzId = clazzCourseTeacher.getClazzId();
            for (Exam exam: examService.listExamByCourseId(courseId)){
                Map<String, Object> map = new HashMap<>();
                if (exam.getType().equals(1)  || exam.getClazzId().equals(clazzId)){
                    map.put("id", exam.getId());
                    map.put("name", exam.getName());
                    map.put("time", exam.getTime());
                    map.put("remark", exam.getRemark());
                    map.put("type", exam.getType());
                    map.put("grade", gradeService.listGradeById(exam.getGradeId()).getName());
                    map.put("clazz", clazzService.listClazzById(exam.getClazzId()).getName());
                    map.put("course", courseService.listCourseById(exam.getCourseId()).getName());
                    result.add(map);
                }
            }
        }
        return ResultUtils.success(result);
    }

    @GetMapping("/listClazzByExam")
    public Message listClazzByExam(@RequestParam("examId") String examId){
        Exam exam = examService.listExamById(Integer.parseInt(examId));
        Map<String, Object> result = new HashMap<>();
        List<Clazz> clazzList;
        if (exam.getClazzId() != null){
            clazzList = new ArrayList<>();
            clazzList.add(clazzService.listClazzById(exam.getClazzId()));
        }else {
            clazzList = clazzService.listClazzByGradeId(exam.getGradeId());
        }
        result.put("clazz", clazzList);
        return ResultUtils.success(result);
    }

    @GetMapping("/listStudentByClazz")
    public Message addScore(@RequestParam("examId") String examId,
                            @RequestParam("clazzId") String clazzId){
        List<Student> studentList = studentService.listStudentByClazz(Integer.parseInt(clazzId));
        return ResultUtils.success(studentList);
    }

    @PostMapping("/addScore")
    public Message addScore(@RequestBody String studentScore){
//        System.out.println(studentScore);
//        System.out.println(JSON.toJSONString(studentScore));
        return null;
    }

    @GetMapping("/getScore")
    public Message getScore(@RequestParam("examId") String examId,
                            @RequestParam("clazzId") String clazzId){
        List<Map<String, Object>> eScoreList = eScoreService.listESCoreByExamIdAndClazzId(Integer.parseInt(examId), Integer.parseInt(clazzId));
        return ResultUtils.success(eScoreList);
    }
}
