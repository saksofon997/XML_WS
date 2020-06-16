package agent.repository.vehicle;

import agent.model.vehicle.Pricelist;
import agent.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    boolean existsByPricelist(Pricelist id);
    boolean existsById(Long id);

    List<Vehicle> findAllByOwnerId(Long ownerId);

    @Query(value = "select count(vehicle.id) from Vehicle as vehicle where vehicle.ownerId = (:owner)")
    int getNoOwnersVehicles(@Param("owner") Long owner);
}
