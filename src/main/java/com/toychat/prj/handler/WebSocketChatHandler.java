package com.toychat.prj.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toychat.prj.common.sequence.SequenceService;
import com.toychat.prj.common.util.Util;
import com.toychat.prj.entity.Chat;
import com.toychat.prj.entity.Chatroom;
import com.toychat.prj.entity.Participant;
import com.toychat.prj.repository.ChatRepository;
import com.toychat.prj.repository.ChatroomRepository;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private ChatroomRepository chatroomRepository;

	@Resource(name = "Util")
	private Util util;

	private final ObjectMapper mapper = new ObjectMapper();
	 private final Set<WebSocketSession> sessions = new HashSet<>();
	private final Map<String, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();
	//private final ConcurrentMap<String, Set<WebSocketSession>> sessions = new ConcurrentHashMap<>();

	// 소켓 연결 확인
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);

	}

	// 소켓 통신 시 메세지의 전송을 다루는 부분
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();

		// 페이로드 -> chatMessageDto로 변환
		Chat chatMessageDto = mapper.readValue(payload, Chat.class);

		// credt put
		String credt = util.getNowDttm();
		chatMessageDto.setCredt(credt);

		// chatId put
		String chatId = String.valueOf(sequenceService.generateSequence(Chat.SEQUENCE_NAME));
		chatMessageDto.setChatId(chatId);

		String chatRoomId = chatMessageDto.getChatroomId();

		// 세션에 chatRoomId 저장
		session.getAttributes().put("chatRoomId", chatRoomId);

		// 메모리 상에 채팅방에 대한 세션 없으면 만들어줌
		if (!chatRoomSessionMap.containsKey(chatRoomId)) {
			chatRoomSessionMap.put(chatRoomId, new HashSet<>());
		}
		Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

		// 입장시
		if (chatMessageDto.getType().equals("ENTER")) {
			// sessions 에 넘어온 session 을 담고,
			chatRoomSession.add(session);

			// 챗방에 participant 추가
			addParticipant(chatMessageDto);
		}

		// 이 부분은 왜 있는거지?
		if (chatRoomSession.size() >= 3) {
			removeClosedSession(chatRoomSession);
		}
		sendMessageToChatRoom(chatMessageDto, chatRoomSession);

		// Redis 저장
		redisTemplate.opsForList().rightPush("chat_" + chatMessageDto.getChatroomId(), chatMessageDto);
	}

	private void addParticipant(Chat chatMessageDto) {
		String status = "01";
		Chatroom room = chatroomRepository.findById(chatMessageDto.getChatroomId())
		        .orElseThrow(() -> new RuntimeException("Chatroom not found"));
		
		// 참여자 build
		Participant participant = Participant.builder().id(chatMessageDto.getId()).nick(chatMessageDto.getNick())
				.joindt(chatMessageDto.getCredt()) // 현재 날짜와 시간으로 joindt 설정
				.build();
		List<Participant> participants = new ArrayList<Participant>();
		if(room.getParticipants() != null) {
			participants = room.getParticipants();
			status = "02"; // 진행중
		}
		
		participants.add(participant);
		room.setParticipants(participants);

		room.setStatus(status);

		// chatroom에 채팅방 등록
		chatroomRepository.save(room);
	}

	// 소켓 종료 확인
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		// 해당 session의 chatroomId 를 들고와야함..
		String chatRoomId = (String) session.getAttributes().get("chatRoomId");
		if (chatRoomId != null) {
//            Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);
//            if (chatRoomSession != null) {
//                chatRoomSession.remove(session);
//                if (chatRoomSession.isEmpty()) {
//                    chatRoomSessionMap.remove(chatRoomId);
//                }
//            }

			// Redis에서 메시지를 가져와 MongoDB에 저장
			List<Object> messages = redisTemplate.opsForList().range("chat_" + chatRoomId, 0, -1);
			if (messages != null && !messages.isEmpty()) {
				for (Object obj : messages) {
					Chat message = (Chat) obj;
					chatRepository.save(message);
				}
				redisTemplate.delete("chat_" + chatRoomId);
			}

			// 채팅방 상태 업데이트
			Chatroom room = chatroomRepository.findById(chatRoomId)
			        .orElseThrow(() -> new RuntimeException("Chatroom not found"));
			
			String credt = util.getNowDttm();
			room.setUpddt(credt);
			room.setStatus("03");
			chatroomRepository.save(room);
		}

		sessions.remove(session);
	}

	// ====== 채팅 관련 메소드 ======
	private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
		 chatRoomSession.removeIf(sess -> !sessions.contains(sess));
	}

	private void sendMessageToChatRoom(Chat chatMessageDto, Set<WebSocketSession> chatRoomSession) {
		chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess, chatMessageDto));// 2
	}

	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 살아있는 세션의 userId 불러오기
	public List<String> getConnectedUsers(String role) {
//		System.out.println("getConnectedUsers");
//		List<String> retList = sessions.values().stream().flatMap(Set::stream) // 모든 세션을 순회
//				.filter(session -> role.equals(session.getAttributes().get("role"))) // role이 ADM인 세션 필터링
//				.map(session -> (String) session.getAttributes().get("userId")) // userId만 추출
//				.distinct() // 중복 제거 (같은 userId가 여러 세션에 있을 수 있음)
//				.collect(Collectors.toList()); // 리스트로 반환
//		for (String user : retList) {
//			System.out.println(user);
//		}
//		return retList;
		return null;
	}

}