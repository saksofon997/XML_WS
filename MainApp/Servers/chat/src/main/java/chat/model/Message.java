package chat.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "message")
@Where(clause="deleted=false")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="message_id_seq",sequenceName="message_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="message_id_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Conversation conversation;

    @Column(name = "sender_id", nullable = false)
    private Long sender_id;

    @Column(name = "receiver_id", nullable = false)
    private Long receiver_id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "deleted")
    private boolean deleted = false;
}
