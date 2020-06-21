package com.spring.zuul.controller;



import com.spring.zuul.client.ChatClient;
import com.spring.zuul.client.UserClient;
import com.spring.zuul.dto.ConversationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ChatClient chatClient;
    @Autowired
    UserClient userClient;

    @GetMapping("/chat/{userId}")
    public ResponseEntity<?> rentalCheckout(@RequestHeader("x-auth") String auth,
                                            @PathVariable Long userId) {

        List<ConversationDTO> conversations = chatClient.getUserConversations(userId, auth);
        System.out.println(conversations);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
