package com.lx.finalproject.service.createaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.user.UserDao;
import com.lx.finalproject.vo.UserVo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // 회원가입
    public void createUser(UserVo userVo) {
        // 중복 확인, 기타 검증 로직 추가 가능
        userDao.createUser(userVo);
    }

    // ID로 사용자 조회
    public UserVo getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }

    // 로그인 처리
    public boolean authenticate(String userId, String password) {
        UserVo user = userDao.loginUser(userId, password);
        return user != null;  // 사용자가 존재하면 true, 없으면 false
    }
    
    // 웹브라우저에 저장할 pk 추출 로직
    public String getUserNameByUserPk(int userPk) {
    	return userDao.getUserNameByUserPk(userPk);
    }
    
    // 아이디 중복 체크 로직
	public boolean checkUserId(String userId) {
		// TODO Auto-generated method stub
		return userDao.checkUserId(userId) > 0;
	}
}
