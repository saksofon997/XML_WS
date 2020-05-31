package vehicle.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name ="brand")
@RequiredArgsConstructor
@Data
@Where(clause="deleted=false")
public class Model {

    @Id
    @SequenceGenerator(name="model_id_seq",sequenceName="model_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="model_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Brand brand;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Model(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
