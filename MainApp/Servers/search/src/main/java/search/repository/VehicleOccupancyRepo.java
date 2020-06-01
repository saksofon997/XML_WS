package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Vehicle;
import search.model.VehicleOccupancy;

import java.util.List;
import java.util.Optional;

public interface VehicleOccupancyRepo extends JpaRepository<VehicleOccupancy, Long> {
    List<VehicleOccupancy> findByVehicle(Vehicle vehicle);

    Optional<VehicleOccupancy> findByIdAndVehicle(Long id, Vehicle vehicle);
}
