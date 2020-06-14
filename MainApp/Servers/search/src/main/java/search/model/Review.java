package search.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Where(clause = "deleted=false")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "customerName")
    private String customerName;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @Column(name = "stars")
    private int stars;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @Column(name = "date")
    private long date;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Review() {
    }
}
