package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.ReviewDTO;
import saga.dto.VehicleOccupancyDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Review;
import vehicle.model.Vehicle;
import vehicle.model.VehicleOccupancy;
import vehicle.repository.VehicleOccupancyRepo;
import vehicle.repository.VehicleRepo;
import vehicle.service.VehicleOccupancyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleOccupancyServiceImpl implements VehicleOccupancyService {

    @Autowired
    VehicleOccupancyRepo vehicleOccupancyRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public VehicleOccupancyDTO convertToDTO(VehicleOccupancy vehicleOccupancy) throws ConversionFailedError {
        try {
            return mapper.map(vehicleOccupancy, VehicleOccupancyDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public VehicleOccupancy convertToModel(VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError {
        try {
            return mapper.map(vehicleOccupancyDTO, VehicleOccupancy.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public List<VehicleOccupancyDTO> getAll(Long vehicleId) throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        List<VehicleOccupancy> vehicleOccupancies = vehicleOccupancyRepo.findByVehicle(vehicle.get());

        return getVehicleOccupancyDTOS(vehicleOccupancies);
    }

    private List<VehicleOccupancyDTO> getVehicleOccupancyDTOS(List<VehicleOccupancy> vehicleOccupancies)
            throws EntityNotFound, ConversionFailedError {
        if (vehicleOccupancies.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<VehicleOccupancyDTO> vehicleOccupancyDTOS = new ArrayList<>();

        for (VehicleOccupancy v : vehicleOccupancies) {
            vehicleOccupancyDTOS.add(convertToDTO(v));
        }

        return vehicleOccupancyDTOS;
    }

    @Override
    public VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO)
            throws ConversionFailedError, DuplicateEntity, EntityNotFound {

        VehicleOccupancy newOccupancy = convertToModel(vehicleOccupancyDTO);

        if(checkAvailable(vehicleId, newOccupancy)) {
            vehicleOccupancyRepo.save(newOccupancy);
            // Todo saga add command here.
        } else {
            throw new DuplicateEntity("Item already exists");
        }
        return vehicleOccupancyDTO;
    }

    private boolean checkAvailable(Long vehicleId, VehicleOccupancy newOccupancy) throws EntityNotFound {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        List<VehicleOccupancy> vehicleOccupancies = vehicleOccupancyRepo.findByVehicle(vehicle.get());

        long startingTimeStamp = newOccupancy.getStartTime();
        long endingTimeStamp = newOccupancy.getEndTime();

        for (VehicleOccupancy v : vehicleOccupancies) {

            if (startingTimeStamp >= v.getStartTime()
                    && endingTimeStamp < v.getEndTime()) {
                return false;
            }
            if (v.getStartTime() >= startingTimeStamp
                    && v.getStartTime() <= endingTimeStamp) {
                return false;
            }
            if (v.getEndTime() > startingTimeStamp
                    && v.getEndTime() <= endingTimeStamp) {
                return false;
            }
        }

        return true;
    }

    @Override
    public VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO)
            throws ConversionFailedError, EntityNotFound, DuplicateEntity {

        VehicleOccupancy newOccupancy = convertToModel(vehicleOccupancyDTO);

        if(checkAvailable(vehicleId, newOccupancy)) {
            vehicleOccupancyRepo.deleteById(id);
            vehicleOccupancyRepo.save(newOccupancy);
            // Todo saga update command here.
        } else {
            throw new DuplicateEntity("Item already exists");
        }
        return vehicleOccupancyDTO;
    }

    @Override
    public VehicleOccupancyDTO delete(Long vehicleId, Long id)
            throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        Optional<VehicleOccupancy> deleted = vehicleOccupancyRepo.findByIdAndVehicle(id, vehicle.get());

        if(!deleted.isPresent()) {
            throw new EntityNotFound("Items not found");
        }
        deleted.get().setDeleted(true);
        vehicleOccupancyRepo.save(deleted.get());
        // vehicleOccupancyRepo.deleteById(id);
        // Todo saga delete command here.
        return convertToDTO(deleted.get());
    }

    @Override
    public List<VehicleOccupancyDTO> getOccupanciesOfGivenPeriod(Long vehicleId, long start_time, long end_time) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Vehicle not found");
        }

        List<VehicleOccupancy> occupancies = vehicleOccupancyRepo.findByVehicleAndByStartAndEndTime(vehicle.get(), start_time, end_time);
        List<VehicleOccupancyDTO> occupanciesDTO = new ArrayList<>();
        for (VehicleOccupancy occ: occupancies){
            occupanciesDTO.add(convertToDTO(occ));
        }

        return occupanciesDTO;
    }
}
