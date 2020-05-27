package vehicle.service;

import vehicle.dto.TransmissionDTO;

import java.util.List;

public interface TransmissionService {
    List<TransmissionDTO> getAll();

    TransmissionDTO add(TransmissionDTO transmissionDTO);

    TransmissionDTO getOne(Long id);

    TransmissionDTO update(Long id, TransmissionDTO transmissionDTO);

    TransmissionDTO delete(Long id);
}
