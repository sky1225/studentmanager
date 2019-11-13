package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    @Select("select * from Student where id=#{id}")
    Student findStudentById(Integer id);

    @Select("select * from student where number=#{number}")
    Student findStudentByNumber(String number);

    @Select("select * from student where name=#{name}")
    List<Student> findStudentByName(String name);

    @Select("select * from student")
    List<Student> findAllStudent();

    @Select("select * from student where clazzId=#{clazzId}")
    List<Student> findStudentByClazzId(Integer clazzId);

    @Select("select * from student where gradeId=#{gradeId}")
    List<Student> findStudentByGradeId(Integer gradeId);

    @Insert("insert into student(number, name, sex, phone, qq, clazzId, gradeId) values" +
            "(#{number}, #{name}, #{sex}, #{phone}, #{qq}, #{clazzId}, #{gradeId})")
    boolean insertStudent(Student student);

    @Update("update student set phone=#{phone}, qq=#{qq}")
    boolean updateStudent(Student student);

    @Delete("delete from student where id=#{id}")
    boolean deleteStudentById(Integer id);

}
