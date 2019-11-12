package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where account=#{account}")
    User findUserByAccount(String account);

    @Insert("insert into user(account, password, name, type) values " +
            "(#{account}, #{password}, #{name}, #{type})")
    boolean insertUser(User user);

    @Update("update user set password=#{password} where account=#{account}")
    boolean updateUserPassword(User user);

    @Delete("delete from user where id=#{id}")
    boolean deleteUser(Integer id);

    @Delete("delete from user where account=#{account}")
    boolean deleteUserByAccount(String account);


}
