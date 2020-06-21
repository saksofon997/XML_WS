package com.spring.zuul.client;

import com.spring.zuul.dto.ConversationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "chat")
public interface ChatClient {

    @GetMapping("/users/{id}/conversation")
    List<ConversationDTO> getUserConversations(@PathVariable Long id,
                                               @RequestHeader("x-auth") String auth);

    @PostMapping("/conversation")
    ConversationDTO createConversation(@RequestBody ConversationDTO conversationDTO,
                                       @RequestHeader("x-auth") String auth);
}
