package com.spring.zuul.dto;

import lombok.Data;


@Data
public class ConversationDTO {

    private Long id;
    private Long user1ID;
    private Long user2ID;
}
