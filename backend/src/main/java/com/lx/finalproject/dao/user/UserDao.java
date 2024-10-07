package com.lx.finalproject.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.UserVo;

@Mapper
public interface UserDao {

    void createUser(UserVo exampleVo);

    UserVo getUserById(int id);
    
    UserVo loginUser(@Param("userId") String userId, @Param("password") String password);
}
