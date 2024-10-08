package com.lx.finalproject.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 기본 생성자 추가
public class ChatMessageVo {

    private String userName;
    private String message;
    private String roomId;
    private String time;

    // 필요한 필드를 초기화하는 생성자
    public ChatMessageVo(String userName, String message) {
        this.userName = userName;
        this.message = message;
       
    }

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

   
}
