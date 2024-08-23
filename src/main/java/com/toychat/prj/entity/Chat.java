package com.toychat.prj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "chats")
public class Chat {
	
	public static final String SEQUENCE_NAME = "chat_sequence";
	
    @Id
    private String chatId;
    private String chatroomId;
    private String id;
    private String nick;
    private String content;
    private String credt;
    private String type; // ENTER, TALK

}