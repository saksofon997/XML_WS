package user.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import user.dto.ObjectFactory;
import user.dto.UserDTO;
import user.exceptions.*;
import user.service.UserService;


import javax.xml.bind.JAXBElement;

@Endpoint
public class UserEndpoint implements WSEndpoint{

    private ObjectFactory objectFactory;
    private UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.objectFactory = new ObjectFactory();
        this.userService = userService;
    }

    // Fuel: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAgentRequest")
    @ResponsePayload
    public JAXBElement<UserDTO> addAgent(@RequestPayload JAXBElement<UserDTO> agent) throws DuplicateEntity, ConversionFailedError {
        UserDTO created = userService.createAgent(agent.getValue());
        return objectFactory.createAddAgentResponse(created);
    }
}
