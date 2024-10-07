package com.lx.finalproject.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.UserVo;

@Mapper
public interface UserDao {

    void createUser(UserVo exampleVo);

    int getUserByUserId(String userId);
    
    UserVo loginUser(@Param("userId") String userId, @Param("userPassword") String password);
}
