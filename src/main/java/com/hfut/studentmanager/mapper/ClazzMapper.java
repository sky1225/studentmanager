package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Clazz;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClazzMapper {

    @Select("select * from clazz where id=#{id}")
    Clazz findClazzById(Integer id);

    @Select("select * from clazz")
    List<Clazz> findAllClazz();

    @Select("select * from clazz where gradeId=#{gradeId}")
    List<Clazz> findClazzByGradeId(Integer gradeId);

    @Select("select name from clazz where id=#{id}")
    String findNameById(Integer id);

    @Select("select id from clazz where name=#{name} and gradeId=#{gradeId}")
    Integer findIdByNameAndGradeId(@Param("name") String name, @Param("gradeId")Integer gradeId);

    @Insert("insert into clazz(name, gradeId) values (#{name}, #{gradeId})")
    boolean insertClazz(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    boolean deleteClazz(Integer id);

    @Delete("delete from clazz where gradeId=#{gradeId}")
    boolean deleteClazzByGradeId(Integer gradeId);
}
