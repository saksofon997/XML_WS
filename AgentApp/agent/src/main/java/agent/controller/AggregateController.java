package agent.controller;

import agent.dto.rental.BundleDTO;
import agent.dto.rental.RentalDTO;
import agent.dto.shared.VehicleOccupancyDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.service.rental.BundleService;
import agent.service.rental.RentalService;
import agent.service.vehicle.VehicleOccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AggregateController {
    @Autowired
    VehicleOccupancyService vehicleOccupancyService;
    @Autowired
    RentalService rentalService;
    @Autowired
    BundleService bundleService;

    @PostMapping(path = "/checkout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rentalCheckout(@RequestBody List<RentalDTO> rentals) throws ConversionFailedError, EntityNotFound {

        for (RentalDTO rentalDTO: rentals){
            List<VehicleOccupancyDTO> occupancies = vehicleOccupancyService.getOccupanciesOfGivenPeriod(rentalDTO.getVehicleId(), rentalDTO.getStartTime(), rentalDTO.getEndTime());
            if (occupancies.size() != 0) {
                return new ResponseEntity<>("One of the vehicles is not available at the chosen period", HttpStatus.BAD_REQUEST);
            } // maybe send which vehicle?
        }
        ArrayList<Long> owners = new ArrayList<>();
        HashMap<String, BundleDTO> bundles = new HashMap<String, BundleDTO>();
        for (RentalDTO rentalDTO: rentals){
            if (!owners.contains(rentalDTO.getOwnerId())) {
                owners.add(rentalDTO.getOwnerId());
            }

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
            BundleDTO response = null;
            try {
                response = bundleService.add(bundle, false);
            } catch (DuplicateEntity duplicateEntity) {
                // Delete created bundles
                for (BundleDTO toDelete: createdBundles.values()){
                    bundleService.delete(toDelete.getId());
                }
                // Inform user
                return new ResponseEntity<>("Cant create bundle", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            createdBundles.put(response.getName(), response);
        }

        for (int i = 0; i < rentals.size(); i++) {
            RentalDTO rental = rentals.get(i);
            if (rental.getBundle() != null) {
                rental.setBundle(createdBundles.get(rental.getBundle().getName()));
            }
            RentalDTO response = null;
            try {
                response = rentalService.add(rental, false);
            } catch (DuplicateEntity duplicateEntity) {
                // Delete created rentals
                for (int j = 0; j < i; j++){
                    rentalService.delete(rentals.get(j).getId());
                }
                // Inform user
                return new ResponseEntity<>("Cant create rental", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // TODO: Whole chat functionality
//        for (Long owner: owners) {
//            ConversationDTO conversation = new ConversationDTO();
//            conversation.setUser1ID(rentals.get(0).getCustomerId());
//            conversation.setUser2ID(owner);
//            chatClient.createConversation(conversation, auth);
//        }

        return new ResponseEntity<>( HttpStatus.OK);
    }
}