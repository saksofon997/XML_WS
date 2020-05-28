package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="category_id_seq",sequenceName="category_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="category_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(){
    }

    public Category(Long id, String name) {
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
