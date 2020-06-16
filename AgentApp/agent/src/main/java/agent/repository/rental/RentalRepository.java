package agent.repository.rental;

import agent.model.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(value = "select rental from Rental as rental where " +
            "(rental.vehicleId = (:vehicle_id))" +
            "and (((rental.startTime <= (:start_time)) and (rental.endTime >= (:end_time)))" +
            "or ((rental.startTime >= (:start_time)) and (rental.startTime < (:end_time)))" +
            "or ((rental.endTime > (:start_time)) and (rental.endTime <= (:end_time))))")
    List<Rental> findByVehicleAndByStartAndEndTime(@Param("vehicle_id") Long vehicle_id,
                                                   @Param("start_time") long start_time,
                                                   @Param("end_time")long end_time);
}