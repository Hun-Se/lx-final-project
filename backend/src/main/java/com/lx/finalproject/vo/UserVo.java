package com.lx.finalproject.vo;

public class UserVo {

    private Integer userPk;         // user_pk
    private String userImg;     // user_img
    private String userId;      // user_id
    private String userPw;      // user_pw
    private Integer userPerm;       // user_perm (권한)
    private String userName;    // user_name
    private String userSsn;     // user_ssn
    private String userMobile;  // user_mobile
    private String userEmail;   // user_email
    private Integer regionPk;       // region_pk (지역 외래키)
    private Integer regionPk2;      // region_pk2 (추가 지역 외래키)
    
    // Getters and Setters
   public Integer getUserPk() {
      return userPk;
   }
   public void setUserPk(Integer userPk) {
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
   public Integer getUserPerm() {
      return userPerm;
   }
   public void setUserPerm(Integer userPerm) {
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
   public Integer getRegionPk() {
      return regionPk;
   }
   public void setRegionPk(Integer regionPk) {
      this.regionPk = regionPk;
   }
   public Integer getRegionPk2() {
      return regionPk2;
   }
   public void setRegionPk2(Integer regionPk2) {
      this.regionPk2 = regionPk2;
   }

   // ToString
   @Override
   public String toString() {
      return "UserVo [userPk=" + userPk + ", userImg=" + userImg + ", userId=" + userId + ", userPw=" + userPw
            + ", userPerm=" + userPerm + ", userName=" + userName + ", userSsn=" + userSsn + ", userMobile="
            + userMobile + ", userEmail=" + userEmail + ", regionPk=" + regionPk + ", regionPk2=" + regionPk2 + "]";
   }
    
}