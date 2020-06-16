package agent.repository.vehicle;

import agent.model.vehicle.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);
}
