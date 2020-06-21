package agent.model.vehicle.mappings;

import agent.model.vehicle.Category;
import agent.model.vehicle.Fuel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoryMapping {
    @Id
    @SequenceGenerator(name="category_mapping_id_seq",sequenceName="category_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="category_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryAgent;

    @Column(name = "category_back_id")
    private Long categoryBackId;
}
