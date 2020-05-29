package com.spring.zuul.controller;

import com.spring.zuul.client.RentalClient;
import com.spring.zuul.dto.BundleDTO;
import com.spring.zuul.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    RentalClient rentalClient;

    @PostMapping("/checkout")
    public ResponseEntity<?> getOrdersAndRestaurant(@RequestHeader("x-auth") String auth, @RequestBody List<RentalDTO> rentals) {

        for (RentalDTO rentalDTO: rentals){
            // call vehicle service to check its availability
            // If one of them is not available, send response to a customer
        }
        HashMap<String, BundleDTO> bundles = new HashMap<String, BundleDTO>();
        for (RentalDTO rentalDTO: rentals){
            BundleDTO bundle = rentalDTO.getBundle();
            if (bundle == null){
                continue;
            }
            if (bundles.containsKey(bundle.getName())){
                continue;
            }
            bundles.put(bundle.getName(), bundle);
        }

        // Create bundles first, they need to be saved so we can put Bundle property of a rental
        for (BundleDTO bundle: bundles.values()){
            ResponseEntity response = rentalClient.createNewBundle(bundle, auth);
            // response.getStatusCode();
            // maybe check for some error ?
        }

        for (RentalDTO rental: rentals){
            ResponseEntity response = rentalClient.createNewRental(rental, auth);
            // response.getStatusCode();
            // maybe check for some error ?
        }

        // if all is well, its a MIRACLE
        // if not... we probably need some try catches here...
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
