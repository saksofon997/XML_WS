package agent.service.vehicle.impl;

import agent.dto.shared.VehicleDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.OperationNotAllowed;
import agent.model.user.User;
import agent.model.vehicle.*;
import agent.model.vehicle.mappings.*;
import agent.repository.user.UserMappingRepo;
import agent.repository.user.UserRepository;
import agent.repository.vehicle.*;
import agent.repository.vehicle.mappingsRepo.*;
import agent.service.vehicle.VehicleService;
import agent.soap.VehicleClient;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import agent.model.user.mappings.UserMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    VehicleClient vehicleClient;

    @Autowired
    VehicleMappingRepo vehicleMappingRepo;

    @Autowired
    FuelRepo fuelRepo;
    @Autowired
    BrandRepo brandRepo;
    @Autowired
    ModelRepo modelRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    TransmissionRepo transmissionRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    FuelMappingRepo fuelMappingRepo;
    @Autowired
    ModelMappingRepo modelMappingRepo;
    @Autowired
    BrandMappingRepo brandMappingRepo;
    @Autowired
    CategoryMappingRepo categoryMappingRepo;
    @Autowired
    TransmissionMappingRepo transmissionMappingRepo;
    @Autowired
    UserMappingRepo userMappingRepo;

    @Value("${PATH_TO_IMAGES:C:\\vehicle_images}")
    private String path_to_images;

    @Override
    public VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError {
        try {
            VehicleDTO vehicleDTO = mapper.map(vehicle, VehicleDTO.class);
            vehicleDTO.getBrand().setModels(null);
            return vehicleDTO;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError {
        try {
            return mapper.map(vehicleDTO, Vehicle.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public List<VehicleDTO> getAll() {
        return null;
    }

    @Override
    public List<VehicleDTO> getOwnersVehicles(Long id) throws ConversionFailedError {
        List<Vehicle> vehicles = vehicleRepo.findAllByOwnerId(id);

        List<VehicleDTO> vehicleDTOS = new ArrayList<VehicleDTO>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(convertToDTO(vehicle));
        }

        return vehicleDTOS;
    }

    @Override
    public VehicleDTO add(VehicleDTO vehicleDTO, MultipartFile[] images, HttpServletRequest request, Boolean isAgent) throws ConversionFailedError, DuplicateEntity, OperationNotAllowed, EntityNotFound {
        System.out.println("User id: ");
        System.out.println(request.getAttribute("userId"));

        if (!isAgent) {
            int noOfVehicles = vehicleRepo.getNoOwnersVehicles(vehicleDTO.getOwnerId());
            System.out.println("Number of users vehicles: ");
            System.out.print(noOfVehicles);
            if (noOfVehicles > 2) {
                throw new OperationNotAllowed("Basic user has already reached maximum number of vehicles.");
            }
        }
        List<String> savedImagesPaths = saveImages(images, request);
        System.out.println(savedImagesPaths);
        vehicleDTO.setImages(savedImagesPaths);
        Vehicle newVehicle = convertToModel(vehicleDTO);


        agent.soap.gen.Vehicle vehicleSOAP = mapper.map(newVehicle, agent.soap.gen.Vehicle.class);
        vehicleSOAP = updateVehicleSOAPParts(vehicleSOAP);
        Long savedId = vehicleClient.createNewVehicle(vehicleSOAP);

        System.out.println("Saved Id in MS backend: " + savedId);
        Vehicle savedVehicle = null;
        if (savedId != 0){
            savedVehicle = vehicleRepo.save(newVehicle);
            VehicleMapping vehicleMapping = new VehicleMapping();
            vehicleMapping.setVehicleAgentId(savedVehicle);
            vehicleMapping.setVehicleBackId(savedId);
            vehicleMappingRepo.save(vehicleMapping);
        }else{
            throw new EntityNotFound("Entity could not be saved on MS app. Check logs for more details.");
        }
        return convertToDTO(savedVehicle);
    }

    @Override
    public agent.soap.gen.Vehicle updateVehicleSOAPParts(agent.soap.gen.Vehicle vehicleToUpdate) throws EntityNotFound {
        Fuel fuel = fuelRepo.findById(vehicleToUpdate.getFuel().getId()).orElse(null);
        if (fuel != null) {
            FuelMapping fm = fuelMappingRepo.findByFuelAgent(fuel);
            if (fm == null) {
                throw new EntityNotFound("Fuel does not exist on MS application");
            }
            vehicleToUpdate.getFuel().setId(fm.getFuelBackId());
        }
        Category category = categoryRepo.findById(vehicleToUpdate.getCategory().getId()).orElse(null);
        if (category != null) {
            CategoryMapping cm = categoryMappingRepo.findByCategoryAgent(category);
            if (cm == null) {
                throw new EntityNotFound("Category does not exist on MS application");
            }
            vehicleToUpdate.getCategory().setId(cm.getCategoryBackId());
        }
        Transmission transmission = transmissionRepo.findById(vehicleToUpdate.getTransmission().getId()).orElse(null);
        if (transmission != null) {
            TransmissionMapping tm = transmissionMappingRepo.findByTransmissionAgent(transmission);
            if (tm == null) {
                throw new EntityNotFound("Transmission does not exist on MS application");
            }
            vehicleToUpdate.getTransmission().setId(tm.getTransmissionBackId());
        }
        Brand brand = brandRepo.findById(vehicleToUpdate.getBrand().getId()).orElse(null);
        if (brand != null) {
            BrandMapping bm = brandMappingRepo.findByBrandAgent(brand);
            if (bm == null) {
                throw new EntityNotFound("Brand does not exist on MS application");
            }
            vehicleToUpdate.getBrand().setId(bm.getBrandBackId());
        }
        Model model = modelRepo.findById(vehicleToUpdate.getModel().getId()).orElse(null);
        if (model != null) {
            ModelMapping mm = modelMappingRepo.findByModelAgent(model);
            if (mm == null) {
                throw new EntityNotFound("Model does not exist on MS application");
            }
            vehicleToUpdate.getModel().setId(mm.getModelBackId());
        }
        User owner = userRepository.findById(vehicleToUpdate.getOwnerId()).orElse(null);
        if (owner != null) {
            UserMapping um = userMappingRepo.findByUserAgentId(owner);
            if (um == null) {
                throw new EntityNotFound("Owner does not exist on MS application");
            }
            vehicleToUpdate.setOwnerId(um.getUserBackId());
        }
        return vehicleToUpdate;
    }

    @Override
    public void updateMileage(Long vehicleId, double mileage) throws EntityNotFound {
        Optional<Vehicle> change = vehicleRepo.findById(vehicleId);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + vehicleId);
        }
        change.get().setMileage(change.get().getMileage() + (long)mileage);

        vehicleRepo.save(change.get());
    }

    @Override
    public VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (!vehicle.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        } else {
            return convertToDTO(vehicle.get());
        }
    }

    @Override
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> change = vehicleRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        Vehicle updatedVehicle = convertToModel(vehicleDTO);
        updatedVehicle.setId(change.get().getId());

        Vehicle savedVehicle = vehicleRepo.save(updatedVehicle);
        return vehicleDTO;
    }

    @Override
    public VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> deleted = vehicleRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        Vehicle vehicle = deleted.get();
        vehicle.setDeleted(true);
        Vehicle deletedVehicle = vehicleRepo.save(vehicle);
        VehicleDTO deletedVehicleDTO = convertToDTO(deletedVehicle);
        return deletedVehicleDTO;
    }

    @Override
    public VehicleDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> deleted = vehicleRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        vehicleRepo.deleteById(id);
        VehicleDTO deletedVehicleDTO = convertToDTO(deleted.get());
        return deletedVehicleDTO;
    }

    public Resource getImage(String fileName, HttpServletRequest request) throws IOException {
        ByteArrayResource resource = null;
        try {
            String realPathtoUploads = path_to_images + "/" + fileName;
            File file = new File(realPathtoUploads);
            Path pathToFile = Paths.get(file.getAbsolutePath());
            System.out.println(realPathtoUploads);

            resource = new ByteArrayResource(Files.readAllBytes(pathToFile));
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + realPathtoUploads);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return resource;
    }

    private List<String> saveImages(MultipartFile[] images, HttpServletRequest request) {
        List<String> savedPaths = new ArrayList<String>();
        System.out.println("Uso u saveImages");
        for (MultipartFile file : images) {
            System.out.println("Uso u for");
            if (!file.isEmpty()) {
                try {
                    String realPathtoUploads = path_to_images;
                    if (!new File(realPathtoUploads).exists()) {
                        new File(realPathtoUploads).mkdir();
                    }
                    System.out.println(realPathtoUploads);
                    System.out.println("Uso u try");

                    String orgName = file.getOriginalFilename();
                    String filePath = realPathtoUploads + "/" + orgName;
                    System.out.println(filePath);
                    File dest = new File(filePath);
                    file.transferTo(dest);
                    savedPaths.add(orgName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return savedPaths;
    }
}
