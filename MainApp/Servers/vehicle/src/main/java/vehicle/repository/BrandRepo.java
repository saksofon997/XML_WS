package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
