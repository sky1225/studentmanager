package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {

    @Select("select * from teacher")
    public List<Teacher> findAllTeacher();

    @Select("select * from teacher where id=#{id}")
    public List<Teacher> findTeacherById(Integer id);

    @Insert("insert into teacher(number, name, sex, phone, qq) values" +
            "(#{number}, #{name}, #{sex}, #{phone}, #{qq})")
    public boolean insertTeacher(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    public boolean deleteTeacher(long id);
}

