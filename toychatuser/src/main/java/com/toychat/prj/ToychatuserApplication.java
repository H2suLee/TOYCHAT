package com.toychat.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class ToychatuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToychatuserApplication.class, args);
	}

}
