package com.hfut.studentmanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemMapper {

    @Select("select * from system")
    public System findSystem();

    @Update("update system set schoolName=#{schoolName}, " +
            "forbidTeacher=#{forbidTeacher}, " +
            "forbidStudent=#{forbidStudent}," +
            "noticeTeacher=#{noticeTeacher}," +
            "noticeStudent=#{noticeStudent}")
    public boolean updateSystem(System system);

}
