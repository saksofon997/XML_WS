package agent.repository.vehicle;

import agent.model.vehicle.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepo extends JpaRepository<Fuel, Long> {

    boolean existsByName(String name);
}
