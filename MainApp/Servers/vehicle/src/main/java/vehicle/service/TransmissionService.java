package vehicle.service;

import saga.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Transmission;
import vehicle.soap.arrays.TransmissionArray;

import java.util.List;

public interface TransmissionService {
    List<TransmissionDTO> getAll() throws EntityNotFound, ConversionFailedError;

    TransmissionDTO add(TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity;

    TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;

    TransmissionDTO convertToDTO(Transmission transmission) throws ConversionFailedError;

    Transmission convertToModel(TransmissionDTO transmissionDTO) throws ConversionFailedError;

    TransmissionArray getAllSOAP();
}
