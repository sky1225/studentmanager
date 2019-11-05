package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClazzMapper {

    @Select("select * from clazz")
    public List<Clazz> findAllClazz();

    @Select("select * from clazz where gradeId=#{gradeId}")
    public List<Clazz> findClazzByGradeId(Integer gradeId);

    @Select("select name from clazz where id=#{id}")
    public String findNameById(Integer id);

    @Select("select id from clazz where name=#{name} and gradeId=#{gradeId}")
    public List<Integer> findIdByNameAndGradeId(String name, Integer gradeId);

    @Insert("insert into clazz(name, gradeId) values (#{name}, #{gradeId})")
    public boolean insertClazz(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    public boolean deleteClazz(Integer id);
}
