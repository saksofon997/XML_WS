package location.mq;

import location.model.LocationMessage;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public void receiveMessage(LocationMessage message) {
        System.out.println("Received <" + message.getMessage() + ">");
    }

}