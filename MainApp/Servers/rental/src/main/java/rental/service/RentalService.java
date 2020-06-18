package rental.service;

import rental.dto.RentalDTO;
import rental.dto.RentalPageDTO;
import rental.exceptions.*;
import rental.model.Rental;
import saga.dto.VehicleOccupancyDTO;

public interface RentalService {

    RentalDTO convertToDTO(Rental rental) throws ConversionFailedError;

    Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError;

    RentalDTO add(RentalDTO rentalDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound;

    RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    RentalDTO update(Long id, RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound;

    void rejectRentalsFromTo(Long vehicleId, VehicleOccupancyDTO occupancyDTO);

    RentalPageDTO getByCustomerAndByStatusPageable(Integer pageNo, String sort, Long id, String status) throws ConversionFailedError, EntityNotFound;

    RentalPageDTO getByOwnerAndByStatusPageable(Integer pageNo, String sort, Long id, String status) throws ConversionFailedError, EntityNotFound;
}
