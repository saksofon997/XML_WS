package agent.model.vehicle.mappings;

import agent.model.vehicle.Brand;
import agent.model.vehicle.Fuel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BrandMapping {
    @Id
    @SequenceGenerator(name="brand_mapping_id_seq",sequenceName="brand_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="brand_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brandAgent;

    @Column(name = "brand_back_id")
    private Long brandBackId;
}
