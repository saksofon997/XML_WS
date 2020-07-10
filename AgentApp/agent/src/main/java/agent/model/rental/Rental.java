package agent.model.rental;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity()
@Data
@Where(clause="deleted=false")
public class Rental {

    @Id
    @SequenceGenerator(name="rentals_id_seq",sequenceName="rentals_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rentals_id_seq")
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "start_time", nullable = false)
    private long startTime;

    @Column(name = "end_time", nullable = false)
    private long endTime;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Bundle bundle;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id")
    private RentalReport report;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "cid")
    private String cid;
}
