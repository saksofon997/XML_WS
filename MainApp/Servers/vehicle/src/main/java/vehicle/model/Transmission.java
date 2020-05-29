package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Transmission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="transmission_id_seq",sequenceName="transmission_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="transmission_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    public Transmission() {
    }

    public Transmission(Long id, String name) {
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
