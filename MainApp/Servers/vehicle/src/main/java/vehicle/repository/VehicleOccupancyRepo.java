package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Vehicle;
import vehicle.model.VehicleOccupancy;

import java.util.List;
import java.util.Optional;

public interface VehicleOccupancyRepo extends JpaRepository<VehicleOccupancy, Long> {
    List<VehicleOccupancy> findByVehicle(Vehicle vehicle);

    Optional<VehicleOccupancy> findByIdAndVehicle(Long id, Vehicle vehicle);
}
