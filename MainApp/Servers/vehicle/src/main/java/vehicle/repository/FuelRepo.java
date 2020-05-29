package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Fuel;

public interface FuelRepo extends JpaRepository<Fuel, Long> {
}
