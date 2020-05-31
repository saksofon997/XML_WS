package vehicle.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name ="category")
@RequiredArgsConstructor
@Data
@Where(clause="deleted=false")
public class Category {

    @Id
    @SequenceGenerator(name="category_id_seq",sequenceName="category_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="category_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "deleted")
    private boolean deleted = false;

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
