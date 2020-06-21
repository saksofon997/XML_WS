package chat.controller;

import chat.dto.ConversationDTO;
import chat.dto.MessageDTO;
import chat.exceptions.ConversionFailedError;
import chat.exceptions.DuplicateEntity;
import chat.exceptions.EntityNotFound;
import chat.service.ConversationService;
import chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ConversationService conversationService;
    @Autowired
    MessageService messageService;

    @PostMapping(path = "/conversation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversationDTO> createNew(@RequestBody ConversationDTO conversationDTO) throws ConversionFailedError, DuplicateEntity {

        ConversationDTO added = conversationService.add(conversationDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/users/{id}/conversation",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConversationDTO>> getUserConversations(@PathVariable Long id) throws ConversionFailedError {

        List<ConversationDTO> added = conversationService.getAllByUser(id);

        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @GetMapping(path = "/conversation/{id}/messages",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageDTO>> getConversation(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        List<MessageDTO> messageDTOS = messageService.getMessages(id);

        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }

    @PostMapping(path = "/message",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> createNewMessage(@RequestBody MessageDTO messageDTO) throws ConversionFailedError, EntityNotFound {

        MessageDTO added = messageService.add(messageDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }
}
