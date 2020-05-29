package rental.service;

import rental.dto.RentalDTO;
import rental.exceptions.*;
import rental.model.Rental;

public interface RentalService {

    RentalDTO convertToDTO(Rental brand) throws ConversionFailedError;

    Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError;

    RentalDTO add(RentalDTO rentalDTO) throws DuplicateEntity, ConversionFailedError;

    RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    RentalDTO update(Long id, RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError;

    RentalDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

}
