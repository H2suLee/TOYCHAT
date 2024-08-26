package com.toychat.prj.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toychat.prj.entity.Chat;
import com.toychat.prj.entity.Chatroom;
import com.toychat.prj.entity.ChatroomInfo;
import com.toychat.prj.entity.User;
import com.toychat.prj.handler.WebSocketAdminHandler;
import com.toychat.prj.service.ChatService;
import com.toychat.prj.service.ChatroomService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private WebSocketAdminHandler webSocketAdminHandler;
	
    @Autowired
    private ChatService chatService;
    
    @Autowired
    private ChatroomService chatroomService;
    
    @PostMapping("/create")
    public Chatroom createRoom(@RequestBody User user){
        return chatroomService.createRoom(user);
    }

    // 사용자 채팅 이력
    @PostMapping("/chatroomList")
    public List<ChatroomInfo> getChatRoomsByUserId(@RequestBody User user) {
    	System.out.println("============================/chat/chatroomList");
        return chatroomService.getChatRoomsByUserId(user);
    }    

    // 채팅 상세
    @PostMapping("/chatList")
    public List<Chat> getChatsByChatroomId(@RequestBody Chatroom chatroom) {
    	System.out.println("============================/chat/chatDetail");
    	return chatService.getChatsByChatroomId(chatroom);
    }    

}
