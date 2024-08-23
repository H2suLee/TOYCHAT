package com.toychat.prj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.toychat.prj.entity.Chatroom;

public interface ChatroomRepository extends MongoRepository<Chatroom, String>{

}
