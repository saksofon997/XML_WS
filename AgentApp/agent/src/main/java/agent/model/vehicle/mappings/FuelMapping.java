package agent.model.vehicle.mappings;

import agent.model.vehicle.Fuel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FuelMapping {
    @Id
    @SequenceGenerator(name="fuel_mapping_id_seq",sequenceName="fuel_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="fuel_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_id")
    private Fuel fuelAgent;

    @Column(name = "fuel_back_id")
    private Long fuelBackId;
}
