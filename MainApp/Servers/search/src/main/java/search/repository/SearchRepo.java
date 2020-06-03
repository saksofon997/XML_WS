package search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import search.model.Vehicle;

import java.util.List;

public interface SearchRepo extends JpaRepository<Vehicle, Long> {

    @Query(value = "select vehicle from Vehicle as vehicle where " +
            "((:loc_lat) <=  (vehicle.locationLatitude + 0.2) and (:loc_lat) >=  (vehicle.locationLatitude - 0.2))" +
            "and ((:loc_long) <=  (vehicle.locationLongitude + 0.2) and (:loc_long) >=  (vehicle.locationLongitude - 0.2))" +

            "and not exists (select occ from VehicleOccupancy as occ where" +
            "(occ.vehicle.id = vehicle.id)"+
            "and (((occ.startTime <= (:start_time)) and (occ.endTime >= (:end_time)))" +
            "or ((occ.startTime >= (:start_time)) and (occ.startTime < (:end_time)))" +
            "or ((occ.endTime > (:start_time)) and (occ.endTime <= (:end_time)))))" +

            "and (((:brands) is null ) or (vehicle.brand.name) in (:brands)) " +
            "and (((:categories) is null ) or (vehicle.category.name) in (:categories)) " +
            "and (((:fuels) is null) or (vehicle.fuel.name) in (:fuels))" +
            "and (((:models) is null) or (vehicle.model.name) in (:models))" +
            "and (((:transmissions) is null) or (vehicle.transmission.name) in (:transmissions))" +
            "")
    Page<Vehicle> getBySearchParams(@Param("brands") List<String> brands,
                                    @Param("categories") List<String> categories,
                                    @Param("fuels") List<String> fuels,
                                    @Param("models") List<String> models,
                                    @Param("transmissions") List<String> transmissions,
                                    @Param("loc_lat") double loc_lat,
                                    @Param("loc_long") double loc_long,
                                    @Param("start_time") long start_time,
                                    @Param("end_time") long end_time,
                                    Pageable pageable);
}
