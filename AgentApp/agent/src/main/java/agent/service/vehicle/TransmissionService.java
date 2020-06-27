package agent.service.vehicle;

import agent.dto.shared.TransmissionDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Transmission;

import java.util.List;

public interface TransmissionService {
    List<TransmissionDTO> getAll() throws EntityNotFound, ConversionFailedError;

    TransmissionDTO add(TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity;

    TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;

    TransmissionDTO convertToDTO(Transmission transmission) throws ConversionFailedError;

    Transmission convertToModel(TransmissionDTO transmissionDTO) throws ConversionFailedError;

    void addTransmissionViaMQ(saga.dto.TransmissionDTO transmissionDTO);

}
