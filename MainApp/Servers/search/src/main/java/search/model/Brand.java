package search.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "brand")
@Where(clause="deleted=false")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="brand_id_seq",sequenceName="brand_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="brand_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Model> models;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Brand(){
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand(Long id, String name, List<Model> models) {
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

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
