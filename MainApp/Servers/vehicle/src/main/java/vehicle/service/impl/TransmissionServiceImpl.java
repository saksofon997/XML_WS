package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.ModelDTO;
import vehicle.dto.ReviewDTO;
import vehicle.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Fuel;
import vehicle.model.Model;
import vehicle.model.Review;
import vehicle.model.Transmission;
import vehicle.repository.TransmissionRepo;
import vehicle.repository.VehicleRepo;
import vehicle.service.TransmissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    TransmissionRepo transmissionRepo;

    @Autowired
    DozerBeanMapper mapper;

    public TransmissionDTO convertToDTO(Transmission transmission) throws ConversionFailedError {
        try {
            return mapper.map(transmission, TransmissionDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

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

        if (!transmissionRepo.existsByName(transmissionDTO.getName()))
            transmissionRepo.save(newTransmission);
        else
            throw new DuplicateEntity("Item with name: "+transmissionDTO.getName()+" already exists");

        return transmissionDTO;
    }

    @Override
    public TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> transmission = transmissionRepo.findById(id);

        if (!transmission.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            return convertToDTO(transmission.get());
    }

    @Override
    public TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound {

        Optional<Transmission> change = transmissionRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(transmissionDTO.getName());

        transmissionRepo.save(change.get());

        return transmissionDTO;
    }

    @Override
    public TransmissionDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> deleted = transmissionRepo.findById(id);

        if (!deleted.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            transmissionRepo.deleteById(id);

        return convertToDTO(deleted.get());
    }
}
