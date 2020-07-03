package user.dto;

import user.model.Company;
import user.soap.WSEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        // Company : get by cid
        QName getCompanyRequest = new QName(WSEndpoint.NAMESPACE_URI, "getCompanyRequest");
        QName getCompanyResponse = new QName(WSEndpoint.NAMESPACE_URI, "getCompanyResponse");
        // Agent : add new
        QName addAgentRequest = new QName(WSEndpoint.NAMESPACE_URI, "addAgentRequest");
        QName addAgentResponse = new QName(WSEndpoint.NAMESPACE_URI, "addAgentResponse");

        QName commonFault = new QName(WSEndpoint.NAMESPACE_URI, "usercommonFault");
    }

    // Company : get by cid
    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "getCompanyRequest")
    public JAXBElement<String> createGetCompanyRequest(String value) {
        return new JAXBElement<>(QNames.getCompanyRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "getCompanyResponse")
    public JAXBElement<Company> createGetCompanyResponse(Company value) {
        return new JAXBElement<>(QNames.getCompanyResponse, Company.class, null, value);
    }

    // Agent : add new
    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "addAgentRequest")
    public JAXBElement<UserDTO> createAddAgentRequest(UserDTO value) {
        return new JAXBElement<>(QNames.addAgentRequest, UserDTO.class, null, value);
    }
    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "addAgentResponse")
    public JAXBElement<UserDTO> createAddAgentResponse(UserDTO value) {
        return new JAXBElement<>(QNames.addAgentResponse, UserDTO.class, null, value);
    }

    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "usercommonFault")
    public JAXBElement<ErrorResponse> createCommonFault(ErrorResponse value) {
        return new JAXBElement<>(QNames.commonFault, ErrorResponse.class, null, value);
    }
}
