package agent.dto.shared;

import agent.dto.user.UserDTO;
import lombok.Data;

@Data
public class ConversationCombinedDTO {
    private Long id;
    private UserDTO user;
}
