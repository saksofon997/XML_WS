package agent.repository.search;

import agent.model.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
            "and ((((:priceFrom) is null) or ((:priceTo) is null)) or" +
            "(((vehicle.pricelist.pricePerDay) >= (:priceFrom)) and ((vehicle.pricelist.pricePerDay) <= (:priceTo))))" +
            "and (((:cdw) is null) or (vehicle.cdw) = (:cdw))" +
            "and (((:mileage) is null) or (vehicle.mileage) <= (:mileage))" +
            "and (((:childSeats) is null) or (vehicle.childSeats) >= (:childSeats))" +
            "and (((:available_mileage) is null) or (:available_mileage) <= (vehicle.availableMileage))" +
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
                                    @Param("cdw") Boolean cdw,
                                    @Param("mileage") Long mileage,
                                    @Param("priceFrom") Long priceFrom,
                                    @Param("priceTo") Long priceTo,
                                    @Param("childSeats") Integer childSeats,
                                    @Param("available_mileage") Long available_mileage,
                                    Pageable pageable);
}
