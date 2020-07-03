package agent.model.rental.mappings;

import agent.model.rental.Rental;
import lombok.Data;

import javax.persistence.*;

/*
 * Mapping rental id from backend app to agent app.
 * Solely for purposes of MQ communication.
 */
@Entity
@Data
public class RentalMapping {
    @Id
    @SequenceGenerator(name="rental_mapping_id_seq",sequenceName="rental_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rental_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental rentalAgentId;

    @Column(name = "rental_back_id")
    private Long rentalBackId;

}
