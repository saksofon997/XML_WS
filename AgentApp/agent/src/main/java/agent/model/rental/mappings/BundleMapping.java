package agent.model.rental.mappings;

import agent.model.rental.Bundle;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BundleMapping {

    @Id
    @SequenceGenerator(name="bundle_mapping_id_seq",sequenceName="bundle_mapping_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="bundle_mapping_id_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "bundle_id")
    private Bundle bundleAgent;

    @Column(name = "bundle_back_id")
    private Long bundleBackId;
}
