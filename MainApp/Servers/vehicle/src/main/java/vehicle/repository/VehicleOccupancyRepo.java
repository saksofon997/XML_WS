package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.VehicleOccupancy;

public interface VehicleOccupancyRepo extends JpaRepository<VehicleOccupancy, Long> {
}
