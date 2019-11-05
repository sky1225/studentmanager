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
    public List<Grade> findAllGrade();

    @Select("select * from grade where id=#{id}")
    public List<Grade> findGradeById(long id);

    @Select("select name from grade where id=#{id}")
    public String findNameById(long id);

    @Insert("insert into grade(name) values (#{grade})")
    public boolean insertGrade(Grade grade);

    @Delete("delete from grade where id=#{id}")
    public boolean deleteGradeById(long id);
}
