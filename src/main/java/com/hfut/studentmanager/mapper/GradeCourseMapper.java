package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.GradeCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeCourseMapper {

    @Select("select * from grade_course where gradeId=#{gradeId} and courseId=#{courseId}")
    GradeCourse findGradeCourseByGradeIdAndCourseId(@Param("gradeId") Integer gradeId, @Param("courseId")Integer courseId);


    @Select("select * from grade_course")
    List<GradeCourse> findAllGradeCourse();

    @Select("select * from grade_course where gradeId=#{gradeId}")
    List<GradeCourse> findGradeCourseByGradeId(Integer gradeId);

    @Select("select * from grade_course where courseId=#{courseId}")
    List<GradeCourse> findGradeCourseByCourseId(Integer courseId);

    @Insert("insert into grade_course(gradeId, courseId) values " +
            "(#{gradeId}, #{courseId})")
    boolean insertGradeCourse(GradeCourse gradeCourse);

    @Delete("delete from grade_course where id=#{id}")
    boolean deleteGradeCourse(Integer id);

}
