package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.ClazzCourseTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClazzCourseTeacherMapper {

    @Select("select * from clazz_course_teacher")
    public List<ClazzCourseTeacher> findAllClazzCourseTeacher();

    @Select("select * from clazz_course_teacher where id=#{id}")
    public List<ClazzCourseTeacher> findClazzCourseTeacherById(Integer id);

    @Select("select * from clazz_course_teacher where clazzId=#{clazzId}")
    public List<ClazzCourseTeacher> findClazzCourseTeacherByClazzId(Integer clazzId);

    @Select("select * from clazz_course_teacher where teacherId=#{teacherId}")
    public List<ClazzCourseTeacher> findClazzCourseTeacherByTeacherId(Integer teacherId);

    @Select("select * from clazz_course_teacher where courseId=#{courseId}")
    public List<ClazzCourseTeacher> findClazzCourseTeacherByCourseId(Integer courseId);

    @Insert("insert into clazz_course_teacher(clazzId, gradeId, courseId, teacherId) values" +
            "(#{clazzId}, #{gradeId}, #{courseId}, #{teacherId})")
    public boolean insertClazzCourseTeacher(ClazzCourseTeacher clazzCourseTeacher);

    @Delete("delete from clazz_course_teacher where id=#{id}")
    public boolean deleteClazzCourseTeacher(Integer id);

}
