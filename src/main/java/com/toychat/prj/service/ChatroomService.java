package com.toychat.prj.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.toychat.prj.common.sequence.SequenceService;
import com.toychat.prj.common.util.Util;
import com.toychat.prj.entity.Chatroom;
import com.toychat.prj.entity.ChatroomInfo;
import com.toychat.prj.entity.Participant;
import com.toychat.prj.entity.User;
import com.toychat.prj.repository.ChatroomRepository;
import com.toychat.prj.repository.UserRepository;

import jakarta.annotation.Resource;

@Service
public class ChatroomService {

	private final MongoTemplate mongoTemplate;

	@Autowired
    public ChatroomService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private ChatroomRepository chatroomRepository;
	
    @Autowired
    private UserRepository userRepository;

	@Resource(name = "Util")
    private Util util;
	
	// 채팅방 생성
	public Chatroom createRoom(User user) {
		 // roomId uuid 생성
		 String roomId = String.valueOf(sequenceService.generateSequence(Chatroom.SEQUENCE_NAME));
		 // credt YYYY-MM-DD HH24:NN:DD 생성
        String credt = util.getNowDttm();

        // Chatroom build
	     Chatroom room = Chatroom.builder()
	                .chatroomId(roomId)
	                .credt(credt)
	                .status("01")
	                //.participants(participants)
	                .build();
	     
	     
	     // chatroom에 채팅방 등록
	     chatroomRepository.save(room);

	     // user에 채팅방 매핑
//	     User userChatrooms = userRepository.findChatroomById(user.getId());
//	     List<String> chatroomList = userChatrooms.getChatrooms();
//	     chatroomList.add(roomId);
//	     user.setChatrooms(chatroomList);
//
//	     userRepository.save(user);


         return room;
	}

	// 채팅방 리스트 by userid
	// roomId, credt, 내용, 상담원, 상태, 마지막 메시지의 chatId, 마지막 메시지의 credt 
	public List<ChatroomInfo> getChatRoomsByUserId(User user) {
		String userId = user.getId();
        // participants._id가 특정 값과 일치하는 항목 필터링
        MatchOperation matchOperation = match(Criteria.where("participants._id").is(userId));

        // Lookup을 사용하여 "chats" 컬렉션에서 데이터 조회
        LookupOperation lookupOperation = lookup("chats", "_id", "chatroomId", "lastMessages");

        // lastMessages 배열을 unwind 하여 각 채팅방의 마지막 메시지를 가져옵니다.
        UnwindOperation unwindOperation = unwind("lastMessages", true);

        // 메시지의 credt 필드를 기준으로 내림차순 정렬
        SortOperation sortOperation = sort(Sort.by(Sort.Direction.DESC, "lastMessages.credt"));

        // 필요한 필드들을 그룹화
        GroupOperation groupOperation = group("_id")
                .first("_id").as("chatroomId")
                .first("participants").as("participants")
                //.first("credt").as("credt")
                .first("status").as("status")
                .first("lastMessages.content").as("lastContent")
                .first("lastMessages.type").as("lastChatType")
                .first("lastMessages.credt").as("lastCredt");

        // 최종 필드를 프로젝트
        ProjectionOperation projectionOperation = project()
                .and("chatroomId").as("chatroomId")
                .and("participants").as("participants")
                //.and("credt").as("credt")
                .and("status").as("status")
                .and("lastContent").as("lastContent")
                .and("lastChatType").as("lastChatType")
                .and("lastCredt").as("lastCredt");

        // 결과를 credt 필드를 기준으로 정렬
        SortOperation finalSortOperation = sort(Sort.by(Sort.Direction.DESC, "lastCredt"));

        // Aggregation 파이프라인을 설정
        Aggregation aggregation = newAggregation(
                matchOperation,
                lookupOperation,
                unwindOperation,
                sortOperation,
                groupOperation,
                projectionOperation,
                finalSortOperation
        );

        // MongoDB에서 Aggregation 실행
        AggregationResults<ChatroomInfo> results = mongoTemplate.aggregate(aggregation, "chatrooms", ChatroomInfo.class);
        List<ChatroomInfo> list = results.getMappedResults();
        
        return list;
	}

	// 채팅 관리 리스트 
	public List<ChatroomInfo> getChatRoomsMngList(HashMap<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return null;
	}

	// 실시간 상담 대기 리스트
	public List<ChatroomInfo> getLiveChatWaitingList(HashMap<String, Object> searchMap) {
        // 쿼리 작성
        Query query = new Query()
                .addCriteria(Criteria.where("status").is("01"))
                .addCriteria(Criteria.where("participants").ne(null));

        query.fields()
             .include("credt")
             .include("status")
             .include("participants.id")
             .include("participants.nick")
             .include("_id");

        // 쿼리 실행
        List<ChatroomInfo> results = mongoTemplate.find(query, ChatroomInfo.class, "chatrooms");
        return results;
	}
	
}
