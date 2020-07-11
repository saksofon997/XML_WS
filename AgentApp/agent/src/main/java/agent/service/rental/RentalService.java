package agent.service.rental;


import agent.dto.rental.RentalDTO;
import agent.dto.rental.RentalPageDTO;
import agent.dto.shared.VehicleOccupancyDTO;
import agent.exceptions.ConflictException;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Rental;

public interface RentalService {

    RentalDTO convertToDTO(Rental rental) throws ConversionFailedError;

    Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError;

    RentalDTO add(RentalDTO rentalDTO, boolean overMq) throws DuplicateEntity, ConversionFailedError, EntityNotFound;

    RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    RentalDTO update(Long id, RentalDTO rentalDTO, boolean overMq) throws EntityNotFound, ConversionFailedError, ConflictException, DuplicateEntity;

    void delete(Long id) throws EntityNotFound;

    void rejectRentalsFromTo(Long vehicleId, VehicleOccupancyDTO occupancyDTO, Long excludeId);

    RentalPageDTO getByCustomerAndByStatusPageable(Integer pageNo, String sort, Long id, String status) throws ConversionFailedError, EntityNotFound;

    RentalPageDTO getByOwnerAndByStatusPageable(Integer pageNo, String sort, Long id, String status) throws ConversionFailedError, EntityNotFound;
}
