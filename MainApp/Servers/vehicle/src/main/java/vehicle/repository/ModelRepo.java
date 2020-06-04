package vehicle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Brand;
import vehicle.model.Model;

public interface ModelRepo extends JpaRepository<Model, Long> {
    boolean existsByName(String name);

    Page<Model> findByBrand(Brand brand, Pageable page);
}
