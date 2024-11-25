package com.lx.finalproject.dto.reportprp;

import java.util.List;

// FileReportDTO.java
public class ReportPrpDTO {
    private String cateUpper;  // 대분류
    private String cateMiddle; // 중분류
    private String cateLower;  // 소분류
    private String content;    // 신고 내용
    private Integer userPk;    // 사용자 ID
    private Integer prpPk;     // 부동산 ID
    private Integer chatPk;    // 채팅방 ID
    private Integer recPk;  // 녹취 PK (optional)
    private Integer flrPk;
    private List<FileInfo> files; // 첨부 파일

    // Getter & Setter


    public String getCateUpper() {
        return cateUpper;
    }

    public void setCateUpper(String cateUpper) {
        this.cateUpper = cateUpper;
    }

    public String getCateMiddle() {
        return cateMiddle;
    }

    public void setCateMiddle(String cateMiddle) {
        this.cateMiddle = cateMiddle;
    }

    public String getCateLower() {
        return cateLower;
    }

    public void setCateLower(String cateLower) {
        this.cateLower = cateLower;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserPk() {
        return userPk;
    }

    public void setUserPk(Integer userPk) {
        this.userPk = userPk;
    }

    public Integer getPrpPk() {
        return prpPk;
    }

    public void setPrpPk(Integer prpPk) {
        this.prpPk = prpPk;
    }

    public Integer getChatPk() {
        return chatPk;
    }

    public void setChatPk(Integer chatPk) {
        this.chatPk = chatPk;
    }

    public Integer getRecPk() {
        return recPk;
    }

    public void setRecPk(Integer recPk) {
        this.recPk = recPk;
    }

    public List<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FileInfo> files) {
        this.files = files;
    }

    public Integer getFlrPk() {
        return flrPk;
    }

    public void setFlrPk(Integer flrPk) {
        this.flrPk = flrPk;
    }

    public static class FileInfo {
        private String name; // 파일 이름
        private String path; // 파일 경로
        private Integer recPk; // 녹취 PK

        // Getter & Setter


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getRecPk() {
            return recPk;
        }

        public void setRecPk(Integer recPk) {
            this.recPk = recPk;
        }
    }
}
