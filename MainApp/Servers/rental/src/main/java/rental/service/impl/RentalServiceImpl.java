package rental.service.impl;

import org.dozer.DozerBeanMapper;
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

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public RentalDTO convertToDTO(Rental rental) throws ConversionFailedError {
        try {
            return mapper.map(rental, RentalDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError {
        try {
            return mapper.map(rentalDTO, Rental.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
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
