package com.toychat.prj.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class User {
    public User() {
		// TODO Auto-generated constructor stub
	}
    
    
	public User(String id, String nick, String pw, String role, List<String> chatrooms, String credt, String upddt,
			String jwt) {
		super();
		this.id = id;
		this.nick = nick;
		this.pw = pw;
		this.role = role;
		this.chatrooms = chatrooms;
		this.credt = credt;
		this.upddt = upddt;
		this.jwt = jwt;
	}


	@Id
    private String id;
    private String nick;
    private String pw;
    private String role;
    private List<String> chatrooms;
    private String credt;
    private String upddt;
    private String jwt;

}