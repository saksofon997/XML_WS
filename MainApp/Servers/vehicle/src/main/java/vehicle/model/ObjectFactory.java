package vehicle.model;

import saga.dto.TransmissionDTO;
import vehicle.soap.ErrorResponse;
import vehicle.soap.FuelEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        QName getFuelNameRequest = new QName(FuelEndpoint.NAMESPACE_URI, "getFuelNameRequest");
        QName getFuelNameResponse = new QName(FuelEndpoint.NAMESPACE_URI, "getFuelNameResponse");
//        QName getBrandName = new QName(FuelEndpoint.NAMESPACE_URI, "getBrandName");

//        QName getUserResponse = new QName(FuelEndpoint.NAMESPACE_URI, "getUserResponse");
        QName commonFault = new QName(FuelEndpoint.NAMESPACE_URI, "commonFault");
    }

    @XmlElementDecl(namespace = FuelEndpoint.NAMESPACE_URI, name = "getFuelNameRequest")
    public JAXBElement<String> createGetFuelRequest(String value) {
        return new JAXBElement<>(QNames.getFuelNameRequest, String.class, null, value);
    }

    @XmlElementDecl(namespace = FuelEndpoint.NAMESPACE_URI, name = "getFuelNameResponse")
    public JAXBElement<Fuel> createGetFuelResponse(Fuel value) {
        return new JAXBElement<>(QNames.getFuelNameResponse, Fuel.class, null, value);
    }

//    @XmlElementDecl(namespace = FuelEndpoint.NAMESPACE_URI, name = "getTransmissionResponse")
//    public JAXBElement<TransmissionDTO> createGetTransmissionResponse(TransmissionDTO value) {
//        return new JAXBElement<>(QNames.getBrandName, TransmissionDTO.class, null, value);
//    }

//    @XmlElementDecl(namespace = WSEndpoint.NAMESPACE_URI, name = "getUserResponse")
//    public JAXBElement<Fuel> createGetUserResponse(User value) {
//        return new JAXBElement<>(QNames.getUserResponse, User.class, null, value);
//    }
//
    @XmlElementDecl(namespace = FuelEndpoint.NAMESPACE_URI, name = "commonFault")
    public JAXBElement<ErrorResponse> createCommonFault(ErrorResponse value) {
        return new JAXBElement<>(QNames.commonFault, ErrorResponse.class, null, value);
    }
}
