package vehicle.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saga.dto.FuelDTO;

@Component
public class VehiclePartsSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(Object testMessage){
        rabbitTemplate.convertAndSend(this.queue.getName(), testMessage);
    }
}
