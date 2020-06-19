package vehicle.model;

import lombok.Data;
import org.hibernate.annotations.Where;
import vehicle.soap.FuelEndpoint;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Data
@Where(clause="deleted=false")
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="fuel_id_seq",sequenceName="fuel_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="fuel_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Fuel(){
    }

    public Fuel(Long id, String name) {
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
