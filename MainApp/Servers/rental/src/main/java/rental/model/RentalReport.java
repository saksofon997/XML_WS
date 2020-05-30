package rental.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
