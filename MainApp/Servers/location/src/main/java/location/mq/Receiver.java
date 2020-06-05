package location.mq;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Receiver {

    public void receiveMessage(Message message) {
        System.out.println("Received <" + Arrays.toString(message.getBody()) + ">");
    }

}