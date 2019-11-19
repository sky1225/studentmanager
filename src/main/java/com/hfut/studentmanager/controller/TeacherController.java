package com.hfut.studentmanager.controller;

import com.hfut.studentmanager.pojo.*;
import com.hfut.studentmanager.service.*;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import com.hfut.studentmanager.utils.jsonBean.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.System;
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
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;

    @PostMapping("/updatePasswordByTeacher")
    public Message updatePasswordByTeacher(@RequestParam("teacherId") String teacherId,
                                           @RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword){
        Teacher teacher = teacherService.listTeacherById(Integer.parseInt(teacherId));
        return userService.updatePassword(teacher.getNumber(), oldPassword, newPassword);
    }

    @GetMapping("/listTeacherByNumber")
    public Message listTeacherByNumber(@RequestParam("number") String number){
        Teacher teacher = teacherService.listTeacherByNumber(number);
        return ResultUtils.success(teacher);
    }

    @GetMapping("/listExam")
    public Message listExam(@RequestParam("teacherId") String teacherId){
        //此教师教的所有课程
        List<ClazzCourseTeacher> clazzCourseTeacherList = clazzCourseTeacherService.listClazzCourseTeacherByTeacherId(Integer.parseInt(teacherId));
        List<Map<String, Object>> result = new ArrayList<>();

        for (ClazzCourseTeacher clazzCourseTeacher: clazzCourseTeacherList){
            //课程id
            Integer courseId = clazzCourseTeacher.getCourseId();
            //班级id
            Integer clazzId = clazzCourseTeacher.getClazzId();
            //某门课程发布的考试
            for (Exam exam: examService.listExamByCourseId(courseId)){
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
                        map.put("clazz", "年级统考");
                    }else {
                        map.put("clazz", clazz.getName());
                    }

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
        if (exam.getClazzId() != 0){
            clazzList = new ArrayList<>();
            clazzList.add(clazzService.listClazzById(exam.getClazzId()));
        }else {
            clazzList = clazzService.listClazzByGradeId(exam.getGradeId());
        }
        result.put("clazz", clazzList);
        return ResultUtils.success(result);
    }

    @GetMapping("/listStudentByClazz")
    public Message listStudentByClazz(@RequestParam("examId") String examId,
                                      @RequestParam("clazzId") String clazzId){
        List<Student> studentList = studentService.listStudentByClazz(Integer.parseInt(clazzId));
        if (studentList == null || studentList.size() == 0) {
            return ResultUtils.error(404, "不存在学生");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Student student: studentList){
            Map<String, Object> map = new HashMap<>();
            Escore escore = eScoreService.listEScoreByExamIdAndStudentId(Integer.parseInt(examId), student.getId());
            if (escore != null){
                map.put("score", escore.getScore().toString());
            }else {
                map.put("score", "");
            }
            map.put("studentId", student.getId());
            map.put("number", student.getNumber());
            map.put("name", student.getName());
            map.put("examId", Integer.parseInt(examId));
            result.add(map);
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/addScore")
    public Message addScore(@RequestBody List<Score> studentScore){
        for (Score score : studentScore) {
            Message result = eScoreService.addEScoreByExamIdAndStudentIdAndCourseId(score.getExamId(), score.getStudentId(),
                    examService.listExamById(score.getExamId()).getCourseId(), Integer.parseInt(score.getScore()));
            if (result.getCode().equals("404")){
                return result;
            }
        }
        return ResultUtils.success();
    }

    @GetMapping("/getScore")
    public Message getScore(@RequestParam("examId") String examId,
                            @RequestParam("clazzId") String clazzId){
        List<Map<String, Object>> eScoreList = eScoreService.listESCoreByExamIdAndClazzId(Integer.parseInt(examId), Integer.parseInt(clazzId));
        return ResultUtils.success(eScoreList);
    }
}
