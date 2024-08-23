package com.toychat.prj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.toychat.prj.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	@Query(value = "{ '_id': ?0 }", fields = "{ 'chatrooms': 1, '_id': 0 }")
	User findChatroomById(String id);

}

