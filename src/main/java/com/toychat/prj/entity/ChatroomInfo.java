package com.toychat.prj.entity;

import java.util.List;

import lombok.Data;
@Data
public class ChatroomInfo {
    private String chatroomId;
    private List<Participant> participants;
    private String credt;
    private String upddt;
    private String status;
    private String lastContent;
    private String lastChatId;
    private String lastCredt;
    private String _id;
    private Participant usr;
    private Participant adm;
    private String category;
    private String memo;

}
