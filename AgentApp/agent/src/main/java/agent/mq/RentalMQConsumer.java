package agent.mq;

import agent.dto.rental.RentalDTO;
import agent.exceptions.ConflictException;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Rental;
import agent.service.rental.RentalService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import saga.dto.VehicleOccupancyDTO;

import java.io.IOException;
import java.util.HashSet;

@Component
public class RentalMQConsumer {
    @Autowired
    RentalService rentalService;
    @Autowired
    DozerBeanMapper mapper;
    @RabbitListener(queues = {"${queue.rental.name}"})
    public void receive(@Payload Message message){

        String className = message.getMessageProperties().getHeader("__TypeId__");
        System.out.println("Pristigla poruka: " + message);
        System.out.println("Class: " + message.getMessageProperties().getHeader("__TypeId__"));
        if(!className.contains("saga")){
            className = className.replace("rental.dto","agent.dto.rental");
            System.out.println("Replaced: " + className);
        }

        Object result = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message.getBody());
            result = objectMapper.readValue(jsonNode.toString(), Class.forName( className));
            System.out.println("Result: " + result);
        }catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        if(result instanceof RentalDTO){
            if(message.getMessageProperties().getHeader("rentalID") != null) {
                try {
                    rentalService.update(message.getMessageProperties().getHeader("rentalID"), convertToDTO(result));
                } catch (EntityNotFound | ConversionFailedError | ConflictException | DuplicateEntity exception) {
                    exception.printStackTrace();
                }
            }else{
                try {
                    rentalService.add((RentalDTO)result);
                } catch (DuplicateEntity | ConversionFailedError | EntityNotFound exception) {
                    exception.printStackTrace();
                }
            }
        }
        if(result instanceof saga.dto.VehicleOccupancyDTO){
            if(message.getMessageProperties().getHeader("vehicleID") != null) {
                // Todo: add occupancy instead of rejecting
                rentalService.rejectRentalsFromTo(message.getMessageProperties().getHeader("vehicleID"),
                        mapper.map(result, agent.dto.shared.VehicleOccupancyDTO.class), message.getMessageProperties().getHeader("excludeID"));
            }

        }
    }

    public RentalDTO convertToDTO(Object rental) throws ConversionFailedError {
        try {
            RentalDTO rentalDTO = mapper.map(rental, RentalDTO.class);
            if (rentalDTO.getBundle() != null) {
                rentalDTO.getBundle().setRentals(new HashSet<>());
            }
            return rentalDTO;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }
}
