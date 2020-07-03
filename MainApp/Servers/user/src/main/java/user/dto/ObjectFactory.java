package user.dto;

import user.soap.WSEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        // Agent : add new
        QName addAgentRequest = new QName(WSEndpoint.NAMESPACE_URI, "addAgentRequest");
        QName addAgentResponse = new QName(WSEndpoint.NAMESPACE_URI, "addAgentResponse");

        QName commonFault = new QName(WSEndpoint.NAMESPACE_URI, "usercommonFault");
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
