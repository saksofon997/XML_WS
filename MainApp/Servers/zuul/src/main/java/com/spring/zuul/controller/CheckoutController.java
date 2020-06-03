package com.spring.zuul.controller;

import com.spring.zuul.client.RentalClient;
import com.spring.zuul.client.VehicleClient;
import com.spring.zuul.dto.BundleDTO;
import com.spring.zuul.dto.RentalDTO;
import com.spring.zuul.dto.VehicleOccupancyDTO;
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
    @Autowired
    VehicleClient vehicleClient;

    @PostMapping("/checkout")
    public ResponseEntity<?> getOrdersAndRestaurant(@RequestHeader("x-auth") String auth, @RequestBody List<RentalDTO> rentals) {

        for (RentalDTO rentalDTO: rentals){
            List<VehicleOccupancyDTO> occupancies = vehicleClient.getOccupanciesOfGivenPeriod(rentalDTO.getVehicleId(), rentalDTO.getStartTime(), rentalDTO.getEndTime(), auth);
            if (occupancies.size() != 0) {
                return new ResponseEntity<>("One of the vehicles is not available at the chosen period", HttpStatus.BAD_REQUEST);
            } // maybe send which vehicle?
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

        HashMap<String, BundleDTO> createdBundles = new HashMap<>();
        // Create bundles first, they need to be saved so we can put Bundle property of a rental
        for (BundleDTO bundle: bundles.values()){
            ResponseEntity response = rentalClient.createNewBundle(bundle, auth);
            if (response.getStatusCode().value() != 201){
                return new ResponseEntity<>("Cant create bundle", HttpStatus.INTERNAL_SERVER_ERROR);
            } // remove created bundles... maybe?
            BundleDTO createdBundle = (BundleDTO) response.getBody();
            createdBundles.put(createdBundle.getName(), createdBundle);
        }

        for (RentalDTO rental: rentals){
            rental.setBundle(createdBundles.get(rental.getBundle().getName()));
            ResponseEntity response = rentalClient.createNewRental(rental, auth);
            if (response.getStatusCode().value() != 201){
                return new ResponseEntity<>("Cant create rental", HttpStatus.INTERNAL_SERVER_ERROR);
            } // remove created rentals... maybe?
        }

        // if all is well, its a MIRACLE
        // if not... we probably need some try catches here...
        return new ResponseEntity<>("Checkout complete", HttpStatus.OK);
    }
}
