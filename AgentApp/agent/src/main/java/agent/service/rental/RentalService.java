package agent.service.rental;


import agent.dto.rental.RentalDTO;
import agent.dto.shared.VehicleOccupancyDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Rental;

public interface RentalService {

    RentalDTO convertToDTO(Rental rental) throws ConversionFailedError;

    Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError;

    RentalDTO add(RentalDTO rentalDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound;

    RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    RentalDTO update(Long id, RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound;

    void rejectRentalsFromTo(Long vehicleId, VehicleOccupancyDTO occupancyDTO);
}
