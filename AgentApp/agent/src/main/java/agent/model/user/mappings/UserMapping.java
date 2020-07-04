package agent.model.user.mappings;

import agent.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserMapping {

    @Id
    @SequenceGenerator(name="user_mapping_id_seq",sequenceName="user_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userAgentId;

    @Column(name = "user_back_id")
    private Long userBackId;
}
