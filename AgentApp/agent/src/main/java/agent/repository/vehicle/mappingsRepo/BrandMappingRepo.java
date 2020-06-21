package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.Brand;
import agent.model.vehicle.mappings.BrandMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandMappingRepo extends JpaRepository<BrandMapping, Long> {
    BrandMapping findByBrandAgent(Brand brand);
}
