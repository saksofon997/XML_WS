package chat.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "conversation")
@Where(clause="deleted=false")
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="conversation_id_seq",sequenceName="conversation_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="conversation_id_seq")
    private Long id;

    @Column(name = "user_1_id", nullable = false)
    private Long user1ID;

    @Column(name = "user_2_id", nullable = false)
    private Long user2ID;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<Message> messages;

    @Column(name = "deleted")
    private boolean deleted = false;
}
