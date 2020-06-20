package vehicle.model;

import vehicle.soap.VehicleEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        QName getFuelsRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getFuelsRequest");
        QName getFuelsResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getFuelsResponse");
        QName createNewVehicleRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "createNewVehicleRequest");
        QName createNewVehicleResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "createNewVehicleResponse");

        QName commonFault = new QName(VehicleEndpoint.NAMESPACE_URI, "commonFault");
    }

    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getFuelsRequest")
    public JAXBElement<String> createGetFuelsRequest(String value) {
        return new JAXBElement<>(QNames.getFuelsRequest, String.class, null, value);
    }

    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getFuelsResponse")
    public JAXBElement<FuelArray> createGetFuelsResponse(FuelArray value) {
        return new JAXBElement<>(QNames.getFuelsResponse, FuelArray.class, null, value);
    }


    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createNewVehicleRequest")
    public JAXBElement<Vehicle> createNewVehicleRequest(Vehicle value) {
        return new JAXBElement<>(QNames.createNewVehicleRequest, Vehicle.class, null, value);
    }

    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createNewVehicleResponse")
    public JAXBElement<Long> createNewVehicleResponse(Long value) {
        return new JAXBElement<>(QNames.createNewVehicleResponse, Long.class, null, value);
    }

    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "commonFault")
    public JAXBElement<ErrorResponse> createCommonFault(ErrorResponse value) {
        return new JAXBElement<>(QNames.commonFault, ErrorResponse.class, null, value);
    }
}
