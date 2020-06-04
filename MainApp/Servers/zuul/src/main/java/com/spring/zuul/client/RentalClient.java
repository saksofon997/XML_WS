package com.spring.zuul.client;

import com.spring.zuul.dto.BundleDTO;
import com.spring.zuul.dto.RentalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "rental")
public interface RentalClient {

    @PostMapping("/rental")
    ResponseEntity<RentalDTO> createNewRental(@RequestBody RentalDTO rentalDTO,
                                              @RequestHeader("x-auth") String auth);

    @PostMapping("/bundle")
    ResponseEntity<BundleDTO> createNewBundle(@RequestBody BundleDTO bundleDTO,
                                              @RequestHeader("x-auth") String auth);

    @DeleteMapping("/bundle/{bundleId}")
    ResponseEntity<?> deleteBundle(@PathVariable Long bundleId,
                                           @RequestHeader("x-auth") String auth);

    @DeleteMapping("/rental/{rentalId}")
    ResponseEntity<?> deleteRental(@PathVariable Long rentalId,
                                   @RequestHeader("x-auth") String auth);
}