package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where account=#{account}")
    public User findUserByAccount(String account);

    @Select("update user set password=#{password} where account=#{account}")
    public boolean updateUserPassword(User user);

}
