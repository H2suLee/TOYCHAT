package com.toychat.prj.handler;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketAdminHandler extends TextWebSocketHandler {
	private final ConcurrentMap<String, Set<WebSocketSession>> admSessions = new ConcurrentHashMap<>();
	private final Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();

	// 오픈시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);

		URI uri = session.getUri();
		if (uri != null) {
			String query = uri.getQuery();
			if (query != null) {
				Map<String, String> queryParams = parseQuery(query);
				String nick = queryParams.get("nick");
				if (!nick.equals("null")) {

					session.getAttributes().put("nick", nick);
					admSessions.computeIfAbsent(nick, k -> ConcurrentHashMap.newKeySet()).add(session);

					System.out.println("Connected user: " + nick);
				}
			}
		}

		broadcastActiveAdmins();
	}

	// 종료시
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String nick = (String) session.getAttributes().get("nick");
		System.out.println("close : " + nick);
		if (nick != null) {
			Set<WebSocketSession> userSessions = admSessions.get(nick);
			if (userSessions != null) {
				userSessions.remove(session);
				System.out.println("userSession removed");
				if (userSessions.isEmpty()) {
					admSessions.remove(nick);
					System.out.println("sessions removed");
					broadcastActiveAdmins();
				}
			}
		}

	}

	// 접속중인 admin 목록 send
	private void broadcastActiveAdmins() {
		for (WebSocketSession session : sessions) {
			if (session.isOpen()) { // 관리자세션인지 아닌지도 검사해야할까..
				String admStr = getActiveAdm();
				System.out.println("actvAdm >>> " + admStr);
				try {
					session.sendMessage(new TextMessage(admStr));
				} catch (IOException e) {
					continue;
				}
			}
		}
	}

	// url 에 딸려오는 쿼리
	private Map<String, String> parseQuery(String query) {
		if (query == null || query.isEmpty()) {
			return Map.of();
		}

		return Arrays.stream(query.split("&")).map(param -> param.split("=", 2)) // Split into key and value with limit
																					// of 2
				.filter(arr -> arr.length == 2) // Ensure both key and value exist
				.collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
	}

	public String getActiveAdm() {
		return admSessions.entrySet().stream().filter(entry -> !entry.getValue().isEmpty()) // 빈 세션 필터링
				.map(Map.Entry::getKey) // 닉네임 추출
				.collect(Collectors.joining(","));
	}
}