package chat.service;

import chat.dto.ConversationDTO;
import chat.dto.MessageDTO;
import chat.exceptions.ConversionFailedError;
import chat.exceptions.DuplicateEntity;
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
public class ConversationService {

    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DozerBeanMapper mapper;

    public ConversationDTO add(ConversationDTO conversationDTO) throws DuplicateEntity, ConversionFailedError {
        boolean check = conversationRepository.existsByUser1IDAndUser2ID(conversationDTO.getUser1ID(), conversationDTO.getUser2ID());
        if (check){
            throw new DuplicateEntity("Conversation already exists");
        }
        check = conversationRepository.existsByUser1IDAndUser2ID(conversationDTO.getUser2ID(), conversationDTO.getUser1ID());
        if (check){
            throw new DuplicateEntity("Conversation already exists");
        }

        Conversation conversation = convertConversationToModel(conversationDTO);
        Conversation saved = conversationRepository.save(conversation);

        return convertConversationToDTO(saved);
    }

    public List<ConversationDTO> getAllByUser(Long id) throws ConversionFailedError {

        List<Conversation> conversations = conversationRepository.getAllByUser1IDOrUser2ID(id, id);
        List<ConversationDTO> conversationDTOS = new ArrayList<>();
        for (Conversation conversation: conversations){
            conversationDTOS.add(convertConversationToDTO(conversation));
        }
        return conversationDTOS;
    }

    public ConversationDTO convertConversationToDTO(Conversation conversation) throws ConversionFailedError {
        try {
            return mapper.map(conversation, ConversationDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public Conversation convertConversationToModel(ConversationDTO conversationDTO) throws ConversionFailedError {
        try {
            return mapper.map(conversationDTO, Conversation.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

}
