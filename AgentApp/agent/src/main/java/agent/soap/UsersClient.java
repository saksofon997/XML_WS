package agent.soap;

import agent.soap.gen.UserDTO;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class UsersClient  extends WebServiceGatewaySupport {

    public JAXBElement<UserDTO> addAgent(UserDTO userDTO) {
        JAXBElement<UserDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/users","addAgentRequest"),
                        UserDTO.class, userDTO);
        JAXBElement<UserDTO> response = (JAXBElement<UserDTO>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println(response.getValue());
        return response;
    }
}
