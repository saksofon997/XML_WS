package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vehicle.model.Pricelist;
import vehicle.model.Vehicle;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    boolean existsByPricelist(Pricelist id);
    boolean existsById(Long id);

    List<Vehicle> findAllByOwnerId(Long ownerId);

    @Query(value = "select count(vehicle.id) from Vehicle as vehicle where vehicle.ownerId = (:owner)")
    int getNoOwnersVehicles(@Param("owner") Long owner);
}
