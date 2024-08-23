package com.toychat.prj.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Participant {
    private String id;
    private String nick;
    private String joindt;
}
