package com.lx.finalproject.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.UserVo;

@Mapper
public interface UserDao {

    void createUser(UserVo userVo);

    UserVo getUserByUserId(String userId);
    
    UserVo loginUser(@Param("userId") String userId, @Param("userPassword") String password);

	String getUserNameByUserPk(int userPk);

	int checkUserId(String userId);
}
