package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.GradeCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeCourseMapper {

    @Select("select * from grade_course where gradeId=#{gradeId} and courseId=#{courseId}")
    public GradeCourse findGradeCourseByGradeIdAndCourseId(@Param("gradeId") Integer gradeId, @Param("courseId")Integer courseId);


    @Select("select * from grade_course")
    public List<GradeCourse> findAllGradeCourse();

    @Select("select * from grade_course where gradeId=#{gradeId}")
    public List<GradeCourse> findGradeCourseByGradeId(Integer gradeId);

    @Select("select * from grade_course where courseId=#{courseId}")
    public List<GradeCourse> findGradeCourseByCourseId(Integer courseId);

    @Insert("insert into grade_course(gradeId, courseId) values " +
            "(#{gradeId}, #{courseId})")
    public boolean insertGradeCourse(GradeCourse gradeCourse);

    @Delete("delete from grade_course where id=#{id}")
    public boolean deleteGradeCourse(Integer id);

}
