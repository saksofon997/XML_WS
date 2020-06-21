package agent.model.vehicle.mappings;

import agent.model.vehicle.Transmission;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class TransmissionMapping {
    @Id
    @SequenceGenerator(name="transmission_mapping_id_seq",sequenceName="transmission_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="transmission_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id")
    private Transmission transmissionAgent;

    @Column(name = "transmission_back_id")
    private Long transmissionBackId;
}
