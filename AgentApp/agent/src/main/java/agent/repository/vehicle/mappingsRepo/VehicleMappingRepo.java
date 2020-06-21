package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.mappings.VehicleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMappingRepo extends JpaRepository<VehicleMapping, Long> {
}
