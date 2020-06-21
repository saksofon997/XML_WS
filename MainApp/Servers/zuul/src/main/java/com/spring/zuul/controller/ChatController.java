package com.spring.zuul.controller;



import com.spring.zuul.client.ChatClient;
import com.spring.zuul.client.UserClient;
import com.spring.zuul.dto.ConversationCombinedDTO;
import com.spring.zuul.dto.ConversationDTO;
import com.spring.zuul.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        List<ConversationCombinedDTO> combinedConversations = new ArrayList<>();

        for (int i = 0; i < conversations.size(); i++) {
            ConversationDTO conv = conversations.get(i);
            ConversationCombinedDTO combined = new ConversationCombinedDTO();
            combined.setId(conv.getId());
            if (Objects.equals(conv.getUser1ID(), userId)) {
                ResponseEntity<UserDTO> response = userClient.getUser(conv.getUser2ID(), auth);
                if (response.getStatusCode().is2xxSuccessful()){
                    combined.setUser(response.getBody());
                } else {
                    continue;
                }
            } else if (Objects.equals(conv.getUser2ID(), userId)) {
                ResponseEntity<UserDTO> response = userClient.getUser(conv.getUser1ID(), auth);
                if (response.getStatusCode().is2xxSuccessful()){
                    combined.setUser(response.getBody());
                } else {
                    continue;
                }
            } else {
                continue;
            }
            combinedConversations.add(combined);
        }

        return new ResponseEntity<>(combinedConversations, HttpStatus.OK);
    }
}
