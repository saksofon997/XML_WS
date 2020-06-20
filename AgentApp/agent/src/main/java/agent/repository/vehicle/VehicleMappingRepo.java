package agent.repository.vehicle;

import agent.model.vehicle.VehicleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMappingRepo extends JpaRepository<VehicleMapping, Long> {
}
