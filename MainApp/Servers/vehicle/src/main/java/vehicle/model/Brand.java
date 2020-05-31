package vehicle.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name ="brand")
@Data
@Where(clause="deleted=false")
public class Brand {

    @Id
    @SequenceGenerator(name="brand_id_seq",sequenceName="brand_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="brand_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    List<Model> models;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Brand(){
        models = new ArrayList<Model>();
    }
    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
        models = new ArrayList<Model>();
    }

    public Brand(Long id, String name, ArrayList<Model> models) {
        this.id = id;
        this.name = name;
        this.models = models;
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(ArrayList<Model> models) {
        this.models = models;
    }
}
