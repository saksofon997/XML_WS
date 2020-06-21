package agent.repository.vehicle;

import agent.model.vehicle.Brand;
import agent.model.vehicle.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model, Long> {
    boolean existsByName(String name);

    Page<Model> findByBrand(Brand brand, Pageable page);
    Model findByName(String name);
}
