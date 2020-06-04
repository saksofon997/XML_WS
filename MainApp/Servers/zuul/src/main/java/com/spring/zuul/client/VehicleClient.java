package com.spring.zuul.client;

import com.spring.zuul.dto.VehicleOccupancyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "vehicle")
public interface VehicleClient {

    @GetMapping("/vehicle/{vehicleId}/occupancy/start/{start_time}/end/{end_time}")
    List<VehicleOccupancyDTO> getOccupanciesOfGivenPeriod(@PathVariable Long vehicleId,
                                                          @PathVariable long start_time,
                                                          @PathVariable long end_time,
                                                          @RequestHeader("x-auth") String auth);
}
