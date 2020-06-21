package agent.model.vehicle.mappings;

import agent.model.vehicle.Model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ModelMapping {
    @Id
    @SequenceGenerator(name="model_mapping_id_seq",sequenceName="model_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="model_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model modelAgent;

    @Column(name = "model_back_id")
    private Long modelBackId;
}
