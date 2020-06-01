package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Pricelist;
import search.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    boolean existsByPricelist(Pricelist id);
}
