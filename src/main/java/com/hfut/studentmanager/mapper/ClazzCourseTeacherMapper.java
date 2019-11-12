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
    List<ClazzCourseTeacher> findAllClazzCourseTeacher();

    @Select("select * from clazz_course_teacher where id=#{id}")
    List<ClazzCourseTeacher> findClazzCourseTeacherById(Integer id);

    @Select("select * from clazz_course_teacher where clazzId=#{clazzId}")
    List<ClazzCourseTeacher> findClazzCourseTeacherByClazzId(Integer clazzId);

    @Select("select * from clazz_course_teacher where teacherId=#{teacherId}")
    List<ClazzCourseTeacher> findClazzCourseTeacherByTeacherId(Integer teacherId);

    @Select("select * from clazz_course_teacher where courseId=#{courseId}")
    List<ClazzCourseTeacher> findClazzCourseTeacherByCourseId(Integer courseId);

    @Insert("insert into clazz_course_teacher(clazzId, gradeId, courseId, teacherId) values" +
            "(#{clazzId}, #{gradeId}, #{courseId}, #{teacherId})")
    boolean insertClazzCourseTeacher(ClazzCourseTeacher clazzCourseTeacher);

    @Delete("delete from clazz_course_teacher where id=#{id}")
    boolean deleteClazzCourseTeacher(Integer id);

    @Delete("delete from clazz_course_teacher where gradeId=#{gradeId}")
    boolean deleteClazzCourseTeacherByGradeId(Integer gradeId);

}
