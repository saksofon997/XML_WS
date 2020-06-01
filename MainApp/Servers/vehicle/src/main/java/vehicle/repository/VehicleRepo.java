package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Pricelist;
import vehicle.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    boolean existsByPricelist(Pricelist id);
    boolean existsById(Long id);
}
