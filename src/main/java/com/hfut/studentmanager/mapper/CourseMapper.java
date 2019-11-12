package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {

    @Select("select * from course where name=#{name}")
    Course findCourseByName(String name);

    @Select("select name from course where id=#{id}")
    String findNameById(Integer id);

    @Select("select id from course where name=#{name}")
    Integer findIdByName(String name);

    @Select("select * from course")
    List<Course> findAllCourse();

    @Select("select * from course where id=#{id}")
    Course findCourseById(Integer id);

    @Insert("insert into course(name) values (#{name})")
    boolean insertCourse(Course course);

    @Delete("delete from course where id=#{id}")
    boolean deleteCourseById(Integer id);

}
