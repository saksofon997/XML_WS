package vehicle.service;

import saga.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface TransmissionService {
    List<TransmissionDTO> getAll() throws EntityNotFound, ConversionFailedError;

    TransmissionDTO add(TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity;

    TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound;

    TransmissionDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}
