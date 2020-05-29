package com.spring.zuul.client;

import com.spring.zuul.dto.BundleDTO;
import com.spring.zuul.dto.RentalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "rental")
public interface RentalClient {

    @PostMapping("/rental")
    ResponseEntity<RentalDTO> createNewRental(@RequestBody RentalDTO rentalDTO,
                                              @RequestHeader("x-auth") String auth);

    @PostMapping("/bundle")
    ResponseEntity<BundleDTO> createNewBundle(@RequestBody BundleDTO bundleDTO,
                                              @RequestHeader("x-auth") String auth);
}