package chat.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private Long id;
    private Long conversation_id;
    private Long sender_id;
    private Long receiver_id;
    private String text;
    private long timestamp;
}
