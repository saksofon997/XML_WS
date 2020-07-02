package rental.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@Where(clause="deleted=false")
public class RentalReport {

    @Id
    @SequenceGenerator(name="rental_reports_id_seq",sequenceName="rental_reports_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rental_reports_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "mileage", nullable = false)
    private double mileage;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;
}
