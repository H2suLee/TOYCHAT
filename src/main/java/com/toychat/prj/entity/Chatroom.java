package com.toychat.prj.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "chatrooms")
public class Chatroom {
	
	public static final String SEQUENCE_NAME = "chatroom_sequence";
	
    @Id
    private String chatroomId;
    private List<Participant> participants;
    private String credt;
    private String upddt;
    private String status;
    private String category;
    private String memo;
}


