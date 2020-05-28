package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dto.TransmissionDTO;
import search.repository.TransmissionRepo;
import search.service.TransmissionService;

import java.util.List;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    TransmissionRepo transmissionRepo;

    @Override
    public List<TransmissionDTO> getAll() {
        return null;
    }

    @Override
    public TransmissionDTO add(TransmissionDTO transmissionDTO) {
        return null;
    }

    @Override
    public TransmissionDTO getOne(Long id) {
        return null;
    }

    @Override
    public TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) {
        return null;
    }

    @Override
    public TransmissionDTO delete(Long id) {
        return null;
    }
}
