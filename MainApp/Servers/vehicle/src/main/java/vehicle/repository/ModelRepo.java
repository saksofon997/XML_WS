package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Brand;
import vehicle.model.Model;

import java.util.List;

public interface ModelRepo extends JpaRepository<Model, Long> {
    boolean existsByName(String name);

    List<Model> findByBrand(Brand brand);
}
