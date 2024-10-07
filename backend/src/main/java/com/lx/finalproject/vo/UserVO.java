package com.lx.finalproject.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer userPk;           // 사용자 번호
    private String userImg;           // 사용자 이미지
    private String userId;            // 사용자 아이디
    private String userPw;            // 사용자 비밀번호
    private Integer userPerm;         // 사용자 권한
    private String userName;          // 사용자 이름
    private String userSsn;           // 사용자 주민등록번호
    private String userMobile;        // 사용자 휴대폰 번호
    private String userEmail;         // 사용자 이메일
    private Integer regionPk;         // 지역 외래키
    private Integer regionPk2;        // 두 번째 지역 외래키 (추가)
	@Override
	public String toString() {
		return "UserVO [userPk=" + userPk + ", userImg=" + userImg + ", userId=" + userId + ", userPw=" + userPw
				+ ", userPerm=" + userPerm + ", userName=" + userName + ", userSsn=" + userSsn + ", userMobile="
				+ userMobile + ", userEmail=" + userEmail + ", regionPk=" + regionPk + ", regionPk2=" + regionPk2 + "]";
	}
    
    
}