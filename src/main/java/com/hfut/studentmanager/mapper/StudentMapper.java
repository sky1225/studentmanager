package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from student where clazzid=#{clazzid}")
    public List<Student> findStudentByClazzid(String clazzid);

    @Select("select * from student where gradeid=#{gradeid}")
    public List<Student> findStudentByGradeid(String gradeid);

    @Select("insert into student(number, name, sex, phone, qq, clazzid, gradeid) values" +
            "#{number}, #{name}, #{sex}, #{phone}, #{qq}, #{clazzid}, #{gradeid}")
    public boolean insertStudent(Student student);

    @Select("update student set phone=#{phone}, qq=#{qq}")
    public boolean updateStudent(Student student);

    @Select("delete from student where number=#{number}")
    public boolean deleteStudent(String number);

}
