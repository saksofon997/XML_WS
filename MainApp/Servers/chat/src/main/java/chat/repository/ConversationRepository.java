package chat.repository;

import chat.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    boolean existsByUser1IDAndUser2ID(Long user1ID, Long user2ID);

    List<Conversation> getAllByUser1IDOrUser2ID(Long user1ID, Long user2ID);
}
