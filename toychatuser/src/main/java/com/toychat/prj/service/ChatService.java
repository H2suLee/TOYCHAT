package com.toychat.prj.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.toychat.prj.entity.Chat;
import com.toychat.prj.entity.Chatroom;
import com.toychat.prj.repository.ChatRepository;

@Service
public class ChatService {
	private final MongoTemplate mongoTemplate;

	@Autowired
    public ChatService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	@Autowired
	private ChatRepository chatRepository;

	// 채팅 리스트
	public List<Chat> getChatsByChatroomId(Chatroom chatroom) {
		String chatroomId = chatroom.getChatroomId();

		MatchOperation matchOperation = match(Criteria.where("chatroomId").is(chatroomId));

        SortOperation finalSortOperation = sort(Sort.by(Sort.Direction.ASC, "credt"));
        
        // Aggregation 파이프라인을 설정
        Aggregation aggregation = newAggregation(
                matchOperation,
                finalSortOperation
        );

        // MongoDB에서 Aggregation 실행
        AggregationResults<Chat> results = mongoTemplate.aggregate(aggregation, "chats", Chat.class);
        List<Chat> list = results.getMappedResults();
		
		return list;
	}
}
