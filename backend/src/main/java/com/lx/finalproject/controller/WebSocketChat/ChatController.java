package com.lx.finalproject.controller.WebSocketChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lx.finalproject.utill.SimpleChatHandler;
import com.lx.finalproject.vo.ChatMessageVo;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {
	
	@Autowired
    SimpleChatHandler simpleChatHandler;
	
	@Autowired
    Gson gson;

    @GetMapping("test")
    public @ResponseBody String test() throws Exception {
        simpleChatHandler.broadcast(gson.toJson(new ChatMessageVo("관리자", "10분 뒤 서버가 종료됩니다.")));
        return "done";
    }

    @GetMapping("rooms")
    public String rooms() {
        return "rooms";  
    }

    @GetMapping("enter")
    public String enter(@RequestParam("userName") String userName) {
        return "chat";  
    }
    
    
}
