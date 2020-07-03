package agent.soap;

import agent.soap.gen.Company;
import agent.soap.gen.UserDTO;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class UsersClient  extends WebServiceGatewaySupport {

    public JAXBElement<Company> getCompany(String cid) {
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/users","getCompanyRequest"),
                        String.class, cid);
        JAXBElement<Company> response = (JAXBElement<Company>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        return response;
    }

    public JAXBElement<UserDTO> addAgent(UserDTO userDTO) {
        JAXBElement<UserDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/users","addAgentRequest"),
                        UserDTO.class, userDTO);
        JAXBElement<UserDTO> response = (JAXBElement<UserDTO>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        return response;
    }
}
