package com.lx.finalproject.vo;

import java.sql.Timestamp;

public class ChatmesVO {
    private Integer chatmesPk;
    private Timestamp chatmesDatetime; // String 대신 Timestamp 사용
    private String chatmesContent;
    private Integer userPkSender;
    private Integer agentPkSender;
    private Integer chatPk;

    public Integer getChatmesPk() {
        return chatmesPk;
    }

    public void setChatmesPk(Integer chatmesPk) {
        this.chatmesPk = chatmesPk;
    }

    public Timestamp getChatmesDatetime() {
        return chatmesDatetime;
    }

    public void setChatmesDatetime(Timestamp chatmesDatetime) {
        this.chatmesDatetime = chatmesDatetime;
    }

    public String getChatmesContent() {
        return chatmesContent;
    }

    public void setChatmesContent(String chatmesContent) {
        this.chatmesContent = chatmesContent;
    }

    public Integer getUserPkSender() {
        return userPkSender;
    }

    public void setUserPkSender(Integer userPkSender) {
        this.userPkSender = userPkSender;
    }

    public Integer getAgentPkSender() {
        return agentPkSender;
    }

    public void setAgentPkSender(Integer agentPkSender) {
        this.agentPkSender = agentPkSender;
    }

    public Integer getChatPk() {
        return chatPk;
    }

    public void setChatPk(Integer chatPk) {
        this.chatPk = chatPk;
    }

    @Override
    public String toString() {
        return "ChatmesVO{" +
               "chatmesPk=" + chatmesPk +
               ", chatmesDatetime=" + chatmesDatetime +
               ", chatmesContent='" + chatmesContent + '\'' +
               ", userPkSender=" + userPkSender +
               ", agentPkSender=" + agentPkSender +
               ", chatPk=" + chatPk +
               '}';
    }
}
