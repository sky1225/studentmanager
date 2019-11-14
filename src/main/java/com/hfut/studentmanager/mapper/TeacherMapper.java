package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {

    @Select("select * from teacher where number=#{number}")
    Teacher findTeacherByNumber(String number);

    @Select("select * from teacher")
    List<Teacher> findAllTeacher();

    @Select("select * from teacher where id=#{id}")
    Teacher findTeacherById(Integer id);

    @Select("select id from teacher where number=#{number}")
    Integer findIdByNumber(@Param("number") String number);

    @Insert("insert into teacher(number, name, sex, phone, qq) values" +
            "(#{number}, #{name}, #{sex}, #{phone}, #{qq})")
    boolean insertTeacher(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    boolean deleteTeacher(Integer id);
}

