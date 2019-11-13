package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.CourseMapper;
import com.hfut.studentmanager.mapper.GradeCourseMapper;
import com.hfut.studentmanager.mapper.GradeMapper;
import com.hfut.studentmanager.pojo.GradeCourse;
import com.hfut.studentmanager.utils.Message;
import com.hfut.studentmanager.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class GradeCourseService {

    @Autowired
    private GradeCourseMapper gradeCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private GradeMapper gradeMapper;

    @Transactional
    public Message addGradeCourse(GradeCourse gradeCourse){
        if (courseMapper.findCourseById(gradeCourse.getCourseId()) == null){
            return ResultUtils.error(404, "该课程不存在");
        }
        if (gradeMapper.findGradeById(gradeCourse.getGradeId()) == null){
            return ResultUtils.error(404, "该年级不存在");
        }
        if (gradeCourseMapper.findGradeCourseByGradeIdAndCourseId(gradeCourse.getGradeId(), gradeCourse.getCourseId()) != null){
            return ResultUtils.error(404, "该年级的该课程已存在");
        }
        if (!gradeCourseMapper.insertGradeCourse(gradeCourse)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(404, "添加该年级课程失败");
        }
        return ResultUtils.success();
    }
}
