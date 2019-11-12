package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Grade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.ManagedBean;
import java.util.List;

@Mapper
@Repository
public interface GradeMapper {

    @Select("select * from grade")
    List<Grade> findAllGrade();

    @Select("select * from grade where id=#{id}")
    Grade findGradeById(Integer id);

    @Select("select name from grade where id=#{id}")
    String findNameById(Integer id);

    @Select("select id from grade where name=#{name}")
    Integer findIdByName(String name);

    @Insert("insert into grade(name) values (#{name})")
    boolean insertGrade(Grade grade);

    @Delete("delete from grade where id=#{id}")
    boolean deleteGradeById(Integer id);
}
