package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="fuel_id_seq",sequenceName="fuel_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="fuel_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

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
}
