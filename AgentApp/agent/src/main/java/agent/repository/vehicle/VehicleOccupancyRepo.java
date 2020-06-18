package agent.repository.vehicle;

import agent.model.vehicle.Vehicle;
import agent.model.vehicle.VehicleOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleOccupancyRepo extends JpaRepository<VehicleOccupancy, Long> {
    List<VehicleOccupancy> findByVehicle(Vehicle vehicle);

    Optional<VehicleOccupancy> findByIdAndVehicle(Long id, Vehicle vehicle);

    @Query(value = "select vehicleOccupancy from VehicleOccupancy as vehicleOccupancy where " +
            "(vehicleOccupancy.vehicle = (:vehicle))" +
            "and (((vehicleOccupancy.startTime <= (:start_time)) and (vehicleOccupancy.endTime >= (:end_time)))" +
            "or ((vehicleOccupancy.startTime >= (:start_time)) and (vehicleOccupancy.startTime < (:end_time)))" +
            "or ((vehicleOccupancy.endTime > (:start_time)) and (vehicleOccupancy.endTime <= (:end_time))))")
    List<VehicleOccupancy> findByVehicleAndByStartAndEndTime(@Param("vehicle") Vehicle vehicle,
                                                             @Param("start_time") long start_time,
                                                             @Param("end_time")long end_time);
}
