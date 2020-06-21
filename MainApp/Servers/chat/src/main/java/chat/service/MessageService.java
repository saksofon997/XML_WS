package chat.service;

import chat.dto.ConversationDTO;
import chat.dto.MessageDTO;
import chat.exceptions.ConversionFailedError;
import chat.exceptions.EntityNotFound;
import chat.model.Conversation;
import chat.model.Message;
import chat.repository.ConversationRepository;
import chat.repository.MessageRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DozerBeanMapper mapper;

    public List<MessageDTO> getMessages(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Conversation> conversation = conversationRepository.findById(id);
        if (!conversation.isPresent()) {
            throw new EntityNotFound("Conversation not found");
        }

        List<Message> messages = conversation.get().getMessages();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message: messages){
            messageDTOS.add(convertMessageToDTO(message));
        }
        return messageDTOS;
    }

    public MessageDTO add(MessageDTO messageDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Conversation> conversation = conversationRepository.findById(messageDTO.getConversation_id());
        if (!conversation.isPresent()) {
            throw new EntityNotFound("Conversation not found");
        }

        Message message = convertMessageToModel(messageDTO);
        message.setConversation(conversation.get());

        Message saved = messageRepository.save(message);

        return convertMessageToDTO(saved);
    }

    public MessageDTO convertMessageToDTO(Message message) throws ConversionFailedError {
        try {
            MessageDTO messageDTO = mapper.map(message, MessageDTO.class);
            messageDTO.setConversation_id(message.getConversation().getId());
            return messageDTO;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public Message convertMessageToModel(MessageDTO messageDTO) throws ConversionFailedError {
        try {
            return mapper.map(messageDTO, Message.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

}
