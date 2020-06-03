package rental.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Where(clause="deleted=false")
public class Bundle {

    @Id
    @SequenceGenerator(name="bundles_id_seq",sequenceName="bundles_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="bundles_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rental> rentals;

    @Column(name = "deleted")
    private boolean deleted = false;
}
