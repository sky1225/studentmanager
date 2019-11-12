package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {

    @Select("select name from course where id=#{id}")
    public String findNameById(Integer id);

    @Select("select id from course where name=#{name}")
    public Integer findIdByName(String name);

    @Select("select * from course")
    public List<Course> findAllCourse();

    @Select("select * from course where id=#{id}")
    public Course findCourseById(Integer id);

    @Insert("insert into course(name) values (#{name})")
    public boolean insertCourse(Course course);

    @Delete("delete from course where id=#{id}")
    public boolean deleteCourseById(Integer id);

}
