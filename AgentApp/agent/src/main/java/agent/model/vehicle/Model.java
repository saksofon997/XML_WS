package agent.model.vehicle;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Where(clause="deleted=false")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Model() {
    }

    public Model(Long id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
