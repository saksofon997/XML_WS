package agent.model.vehicle;

import lombok.Data;

import javax.persistence.*;


/*
 * Mapping vehicle id from backend app to agent app.
 * Solely for purposes of SOAP communication.
 */
@Entity
@Data
public class VehicleMapping {
    @Id
    @SequenceGenerator(name="vehicle_mapping_id_seq",sequenceName="vehicle_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicle_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleAgentId;

    @Column(name = "vehicle_back_id")
    private Long vehicleBackId;

}
