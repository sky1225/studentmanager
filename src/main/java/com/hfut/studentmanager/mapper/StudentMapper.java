package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    @Select("select * from student while number=#{number}")
    public Student findStudentByNumber(String number);

    @Select("select * from student while name=#{name}")
    public List<Student> findStudentByName(String name);

    @Select("select * from student")
    public List<Student> findAllStudent();

    @Select("select * from student where clazzId=#{clazzId}")
    public List<Student> findStudentByClazzId(String clazzId);

    @Select("select * from student where gradeId=#{gradeId}")
    public List<Student> findStudentByGradeId(String gradeId);

    @Insert("insert into student(number, name, sex, phone, qq, clazzId, gradeId) values" +
            "(#{number}, #{name}, #{sex}, #{phone}, #{qq}, #{clazzId}, #{gradeId})")
    public boolean insertStudent(Student student);

    @Update("update student set phone=#{phone}, qq=#{qq}")
    public boolean updateStudent(Student student);

    @Delete("delete from student where number=#{number}")
    public boolean deleteStudent(String number);

}
