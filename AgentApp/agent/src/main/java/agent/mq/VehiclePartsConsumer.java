package agent.mq;

import agent.service.vehicle.FuelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RabbitListener(queues = {"${queue.vehicleParts.name}"})
    public void receive(@Payload Message testMessage){
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

    }

}
