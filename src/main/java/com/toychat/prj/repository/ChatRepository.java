package com.toychat.prj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.toychat.prj.entity.Chat;

public interface ChatRepository extends MongoRepository<Chat, String>{

}