package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.Fuel;
import agent.model.vehicle.mappings.FuelMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelMappingRepo extends JpaRepository<FuelMapping, Long> {
    FuelMapping findByFuelAgent(Fuel fuel);
}
