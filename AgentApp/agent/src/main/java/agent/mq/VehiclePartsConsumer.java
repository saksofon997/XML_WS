package agent.mq;

import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.service.vehicle.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import saga.dto.FuelDTO;

import java.io.IOException;

@Component
public class VehiclePartsConsumer {
    @Autowired
    FuelService fuelService;

    @Autowired
    TransmissionService transmissionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    VehicleOccupancyService vehicleOccupancyService;

    @Autowired
    DozerBeanMapper mapper;

    @RabbitListener(queues = {"${queue.vehicleParts.name}"})
    public void receive(@Payload Message testMessage) throws ConversionFailedError, EntityNotFound, DuplicateEntity {
        String className = testMessage.getMessageProperties().getHeader("__TypeId__");
        System.out.println("Pristigla poruka: " + testMessage);
        System.out.println("Class: " + testMessage.getMessageProperties().getHeader("__TypeId__"));

        Object result = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(testMessage.getBody());
            result = objectMapper.readValue(jsonNode.toString(), Class.forName(className));

        }catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        if (result instanceof saga.dto.FuelDTO) {
            fuelService.addFuelViaMQ((saga.dto.FuelDTO)result);
            System.out.println("Result: " + ((FuelDTO) result).getName());
        }
        if (result instanceof saga.dto.CategoryDTO) {
            categoryService.addCategoryViaMQ((saga.dto.CategoryDTO)result);
            System.out.println("Result: " + ((saga.dto.CategoryDTO) result).getName());
        }
        if (result instanceof saga.dto.TransmissionDTO) {
            transmissionService.addTransmissionViaMQ((saga.dto.TransmissionDTO)result);
            System.out.println("Result: " + ((saga.dto.TransmissionDTO) result).getName());
        }
        if (result instanceof saga.dto.ModelDTO) {
            modelService.addModelViaMQ((saga.dto.ModelDTO)result, (Long) testMessage.getMessageProperties().getHeader("brandID"));
            System.out.println("Result: " + ((saga.dto.ModelDTO) result).getName());
        }
        if (result instanceof saga.dto.BrandDTO) {
            brandService.addBrandViaMQ((saga.dto.BrandDTO)result);
            System.out.println("Result: " + ((saga.dto.BrandDTO) result).getName());
        }
        if (result instanceof saga.dto.VehicleOccupancyDTO) {
            Long vehicleID = testMessage.getMessageProperties().getHeader("vehicleID");
            vehicleOccupancyService.add(vehicleID, mapper.map(result, agent.dto.shared.VehicleOccupancyDTO.class), true);
            System.out.println("Result: " + (mapper.map(result, agent.dto.shared.VehicleOccupancyDTO.class)).getType() + " occupancy");
        }
    }

}
