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
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    RentalClient rentalClient;
    @Autowired
    VehicleClient vehicleClient;

    @PostMapping("/checkout")
    public ResponseEntity<?> rentalCheckout(@RequestHeader("x-auth") String auth, @RequestBody List<RentalDTO> rentals) {

        for (RentalDTO rentalDTO: rentals){
            List<VehicleOccupancyDTO> occupancies = vehicleClient.getOccupanciesOfGivenPeriod(rentalDTO.getVehicleId(), rentalDTO.getStartTime(), rentalDTO.getEndTime(), auth);
            if (occupancies.size() != 0) {
                return new ResponseEntity<>("One of the vehicles is not available at the chosen period", HttpStatus.BAD_REQUEST);
            } // maybe send which vehicle?
        }
        HashMap<String, BundleDTO> bundles = new HashMap<String, BundleDTO>();
        for (RentalDTO rentalDTO: rentals){
            BundleDTO bundle = rentalDTO.getBundle();
            System.out.println(bundle);
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
                // Delete created bundles
                for (BundleDTO toDelete: createdBundles.values()){
                    rentalClient.deleteBundle(toDelete.getId(), auth);
                }
                // Inform user
                return new ResponseEntity<>("Cant create bundle", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            BundleDTO createdBundle = (BundleDTO) response.getBody();
            createdBundles.put(createdBundle.getName(), createdBundle);
        }

        for (int i = 0; i < rentals.size(); i++) {
            RentalDTO rental = rentals.get(i);
            if (rental.getBundle() != null) {
                rental.setBundle(createdBundles.get(rental.getBundle().getName()));
            }
            ResponseEntity response = rentalClient.createNewRental(rental, auth);
            if (response.getStatusCode().value() != 201){
                // Delete created rentals
                for (int j = 0; j < i; j++){
                    rentalClient.deleteRental(rentals.get(j).getId(), auth);
                }
                // Inform user
                return new ResponseEntity<>("Cant create rental", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
