package user.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import user.dto.ObjectFactory;
import user.dto.UserDTO;
import user.exceptions.*;
import user.model.Company;
import user.repository.CompanyRepository;
import user.service.UserService;


import javax.xml.bind.JAXBElement;

@Endpoint
public class UserEndpoint implements WSEndpoint{

    private ObjectFactory objectFactory;
    private UserService userService;
    private CompanyRepository companyRepository;

    @Autowired
    public UserEndpoint(UserService userService, CompanyRepository companyRepository) {
        this.objectFactory = new ObjectFactory();
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    // Company : get by cid
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCompanyRequest")
    @ResponsePayload
    public JAXBElement<Company> getCompany(@RequestPayload JAXBElement<String> cid) throws DuplicateEntity, ConversionFailedError {
        Company company = companyRepository.findByCid(cid.getValue());
        return objectFactory.createGetCompanyResponse(company);
    }

    // Agent : add new
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAgentRequest")
    @ResponsePayload
    public JAXBElement<UserDTO> addAgent(@RequestPayload JAXBElement<UserDTO> agent) throws DuplicateEntity, ConversionFailedError {
        UserDTO created = userService.createAgent(agent.getValue());
        return objectFactory.createAddAgentResponse(created);
    }
}
