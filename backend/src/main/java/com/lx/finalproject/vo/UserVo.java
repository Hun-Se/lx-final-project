package com.lx.finalproject.vo;

public class UserVo {

    private int userPk;         // user_pk
    private String userImg;     // user_img
    private String userId;      // user_id
    private String userPw;      // user_pw
    private int userPerm;       // user_perm (권한)
    private String userName;    // user_name
    private String userSsn;     // user_ssn
    private String userMobile;  // user_mobile
    private String userEmail;   // user_email
    private int regionPk;       // region_pk (지역 외래키)
    private int regionPk2;      // region_pk2 (추가 지역 외래키)

    // 기본 생성자
    public UserVo() {}

    // 매개변수 있는 생성자
    public UserVo(int userPk, String userImg, String userId, String userPw, int userPerm, 
                  String userName, String userSsn, String userMobile, String userEmail,
                  int regionPk, int regionPk2) {
        this.userPk = userPk;
        this.userImg = userImg;
        this.userId = userId;
        this.userPw = userPw;
        this.userPerm = userPerm;
        this.userName = userName;
        this.userSsn = userSsn;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.regionPk = regionPk;
        this.regionPk2 = regionPk2;
    }

	public int getUserPk() {
		return userPk;
	}

	public void setUserPk(int userPk) {
		this.userPk = userPk;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public int getUserPerm() {
		return userPerm;
	}

	public void setUserPerm(int userPerm) {
		this.userPerm = userPerm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(String userSsn) {
		this.userSsn = userSsn;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getRegionPk() {
		return regionPk;
	}

	public void setRegionPk(int regionPk) {
		this.regionPk = regionPk;
	}

	public int getRegionPk2() {
		return regionPk2;
	}

	public void setRegionPk2(int regionPk2) {
		this.regionPk2 = regionPk2;
	}    
    
    
}
