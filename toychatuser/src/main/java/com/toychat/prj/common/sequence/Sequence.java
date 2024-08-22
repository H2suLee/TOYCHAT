package com.toychat.prj.common.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "database_sequences")
public class Sequence {
	@Id
	private String id;
	
	private long seq;
}
