package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where account=#{account}")
    public User findUserByAccount(String account);

    @Insert("insert into user(account, password, name, type) values " +
            "(#{account}, #{password}, #{name}, #{type})")
    public boolean insertUser(User user);

    @Update("update user set password=#{password} where account=#{account}")
    public boolean updateUserPassword(User user);

    @Delete("delete from user where id=#{id}")
    public boolean deleteUser(Integer id);

}
