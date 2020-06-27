package agent.service.vehicle.impl;

import agent.dto.shared.TransmissionDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Fuel;
import agent.model.vehicle.Transmission;
import agent.model.vehicle.mappings.FuelMapping;
import agent.model.vehicle.mappings.TransmissionMapping;
import agent.repository.vehicle.TransmissionRepo;
import agent.repository.vehicle.mappingsRepo.TransmissionMappingRepo;
import agent.service.vehicle.TransmissionService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    TransmissionRepo transmissionRepo;

    @Autowired
    TransmissionMappingRepo transmissionMappingRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public TransmissionDTO convertToDTO(Transmission transmission) throws ConversionFailedError {
        try {
            return mapper.map(transmission, TransmissionDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Transmission convertToModel(TransmissionDTO transmissionDTO) throws ConversionFailedError {
        try {
            return mapper.map(transmissionDTO, Transmission.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public List<TransmissionDTO> getAll() throws EntityNotFound, ConversionFailedError {

        List<Transmission> transmissions = transmissionRepo.findAll();

        if (transmissions.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<TransmissionDTO> transmissionDTOS = new ArrayList<>();

        for (Transmission t : transmissions) {
            transmissionDTOS.add(convertToDTO(t));
        }

        return transmissionDTOS;
    }

    @Override
    public TransmissionDTO add(TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity {

        Transmission newTransmission = convertToModel(transmissionDTO);

        if (!transmissionRepo.existsByName(transmissionDTO.getName())) {
            Transmission savedTransmission = transmissionRepo.save(newTransmission);
        } else {
            throw new DuplicateEntity("Item with name: " + transmissionDTO.getName() + " already exists");
        }
        return transmissionDTO;
    }

    @Override
    public TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> transmission = transmissionRepo.findById(id);

        if (!transmission.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }else {
            return convertToDTO(transmission.get());
        }
    }

    @Override
    public TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> change = transmissionRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        change.get().setName(transmissionDTO.getName());

        Transmission savedTransmission = transmissionRepo.save(change.get());
        transmissionDTO.setId(savedTransmission.getId());

        return transmissionDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> deleted = transmissionRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }else {
            deleted.get().setDeleted(true);
            transmissionRepo.save(deleted.get());
        }
    }

    @Override
    public void addTransmissionViaMQ(saga.dto.TransmissionDTO transmissionDTO){
        Transmission transmission = transmissionRepo.findByName(transmissionDTO.getName());
        if (transmission == null) {
            transmission = mapper.map(transmissionDTO, Transmission.class);
            transmission.setId(null);
            transmission = transmissionRepo.save(transmission);
        }
        TransmissionMapping tm = transmissionMappingRepo.findByTransmissionAgent(transmission);
        if (tm == null) {
            tm = new TransmissionMapping();
            tm.setTransmissionAgent(transmission);
            tm.setTransmissionBackId(transmission.getId());
            transmissionMappingRepo.save(tm);
        }
    }
}
