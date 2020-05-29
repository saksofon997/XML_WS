package rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rental.dto.RentalDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.model.Rental;
import rental.repository.RentalRepository;
import rental.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public RentalDTO convertToDTO(Rental brand) throws ConversionFailedError {
        return null;
    }

    @Override
    public Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO add(RentalDTO rentalDTO) throws DuplicateEntity, ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO update(Long id, RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        return null;
    }
}
