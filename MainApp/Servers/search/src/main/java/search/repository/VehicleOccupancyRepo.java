package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.VehicleOccupancy;

public interface VehicleOccupancyRepo extends JpaRepository<VehicleOccupancy, Long> {
}
