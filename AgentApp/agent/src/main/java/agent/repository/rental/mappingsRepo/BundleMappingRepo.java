package agent.repository.rental.mappingsRepo;

import agent.model.rental.Bundle;
import agent.model.rental.mappings.BundleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundleMappingRepo  extends JpaRepository<BundleMapping, Long> {
    BundleMapping findByBundleAgent(Bundle bundle);
}
