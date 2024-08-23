package com.toychat.prj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.toychat.prj.handler.WebSocketAdminHandler;
import com.toychat.prj.handler.WebSocketChatHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{

	// 핸들러
	private final WebSocketChatHandler webSocketChatHandler;
	private final WebSocketAdminHandler webSocketAdminHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// ws://주소:포트/ws/chat로 요청이 들어오면 웹소켓 핸드쉐이킹
		registry.addHandler(webSocketChatHandler, "/ws/chat").setAllowedOrigins("*");
		registry.addHandler(webSocketAdminHandler, "/ws/adminOnList").setAllowedOrigins("*");
	}

}
