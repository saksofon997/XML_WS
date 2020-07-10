package vehicle.soap.arrays;

import saga.dto.VehicleOccupancyDTO;
import vehicle.model.Vehicle;
import vehicle.soap.VehicleEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        // Fuel : get All
        QName getFuelsRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getFuelsRequest");
        QName getFuelsResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getFuelsResponse");
        // Brand : get All
        QName getBrandsRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getBrandsRequest");
        QName getBrandsResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getBrandsResponse");
        // Category : get All
        QName getCategoriesRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getCategoriesRequest");
        QName getCategoriesResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getCategoriesResponse");
        // Transmission : get All
        QName getTransmissionsRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getTransmissionsRequest");
        QName getTransmissionsResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getTransmissionsResponse");
        // Model : get All
        QName getModelsRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getModelsRequest");
        QName getModelsResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getModelsResponse");
        // Vehicle : get All
        QName getVehiclesRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getVehiclesRequest");
        QName getVehiclesResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getVehiclesResponse");
        // Vehicle : get One
        QName getOneVehicleRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "getOneVehicleRequest");
        QName getOneVehicleResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "getOneVehicleResponse");
        // Vehicle : update One
        QName updateOneVehicleRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "updateOneVehicleRequest");
        QName updateOneVehicleResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "updateOneVehicleResponse");
        // Vehicle : add One
        QName createNewVehicleRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "createNewVehicleRequest");
        QName createNewVehicleResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "createNewVehicleResponse");
        // Occupancy : add One
        QName createOccupancyRequest = new QName(VehicleEndpoint.NAMESPACE_URI, "createOccupancyRequest");
        QName createOccupancyResponse = new QName(VehicleEndpoint.NAMESPACE_URI, "createOccupancyResponse");

        QName commonFault = new QName(VehicleEndpoint.NAMESPACE_URI, "commonFault");
    }

    // Fuel : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getFuelsRequest")
    public JAXBElement<String> createGetFuelsRequest(String value) {
        return new JAXBElement<>(QNames.getFuelsRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getFuelsResponse")
    public JAXBElement<FuelArray> createGetFuelsResponse(FuelArray value) {
        return new JAXBElement<>(QNames.getFuelsResponse, FuelArray.class, null, value);
    }

    // Brand : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getBrandsRequest")
    public JAXBElement<String> createGetBrandsRequest(String value) {
        return new JAXBElement<>(QNames.getBrandsRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getBrandsResponse")
    public JAXBElement<BrandArray> createGetBrandsResponse(BrandArray value) {
        return new JAXBElement<>(QNames.getBrandsResponse, BrandArray.class, null, value);
    }

    // Category : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getCategoriesRequest")
    public JAXBElement<String> createGetCategoriesRequest(String value) {
        return new JAXBElement<>(QNames.getCategoriesRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getCategoriesResponse")
    public JAXBElement<CategoryArray> createGetCategoriesResponse(CategoryArray value) {
        return new JAXBElement<>(QNames.getCategoriesResponse, CategoryArray.class, null, value);
    }

    // Transmission : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getTransmissionsRequest")
    public JAXBElement<String> createGetTransmissionsRequest(String value) {
        return new JAXBElement<>(QNames.getTransmissionsRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getTransmissionsResponse")
    public JAXBElement<TransmissionArray> createGetTransmissionsResponse(TransmissionArray value) {
        return new JAXBElement<>(QNames.getTransmissionsResponse, TransmissionArray.class, null, value);
    }

    // Model : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getModelsRequest")
    public JAXBElement<Long> createGetModelsRequest(Long value) {
        return new JAXBElement<>(QNames.getModelsRequest, Long.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getModelsResponse")
    public JAXBElement<ModelArray> createGetModelsResponse(ModelArray value) {
        return new JAXBElement<>(QNames.getModelsResponse, ModelArray.class, null, value);
    }

    // Vehicle : get All
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getVehiclesRequest")
    public JAXBElement<String> createGetVehiclesRequest(String value) {
        return new JAXBElement<>(QNames.getVehiclesRequest, String.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getVehiclesResponse")
    public JAXBElement<VehicleArray> createGetVehiclesResponse(VehicleArray value) {
        return new JAXBElement<>(QNames.getVehiclesResponse, VehicleArray.class, null, value);
    }

    // Vehicle : get One
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getOneVehicleRequest")
    public JAXBElement<Long> createGetOneVehicleRequest(Long value) {
        return new JAXBElement<>(QNames.getOneVehicleRequest, Long.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "getOneVehicleResponse")
    public JAXBElement<Vehicle> createGetOneVehicleResponse(Vehicle value) {
        return new JAXBElement<>(QNames.getOneVehicleResponse, Vehicle.class, null, value);
    }

    // Vehicle : update One
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "updateOneVehicleRequest")
    public JAXBElement<Vehicle> createUpdateOneVehicleRequest(Vehicle value) {
        return new JAXBElement<>(QNames.updateOneVehicleRequest, Vehicle.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "updateOneVehicleResponse")
    public JAXBElement<Vehicle> CreateUpdateOneVehicleResponse(Vehicle value) {
        return new JAXBElement<>(QNames.updateOneVehicleResponse, Vehicle.class, null, value);
    }

    // Vehicle : add One
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createNewVehicleRequest")
    public JAXBElement<Vehicle> createNewVehicleRequest(Vehicle value) {
        return new JAXBElement<>(QNames.createNewVehicleRequest, Vehicle.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createNewVehicleResponse")
    public JAXBElement<Long> createNewVehicleResponse(Long value) {
        return new JAXBElement<>(QNames.createNewVehicleResponse, Long.class, null, value);
    }
    // Todo: adapt
    // Vehicle : add One
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createOccupancyRequest")
    public JAXBElement<VehicleOccupancyDTO> createOccupancyRequest(VehicleOccupancyDTO value) {
        return new JAXBElement<>(QNames.createOccupancyRequest, VehicleOccupancyDTO.class, null, value);
    }
    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "createOccupancyResponse")
    public JAXBElement<Boolean> createOccupancyResponse(Boolean value) {
        return new JAXBElement<>(QNames.createOccupancyResponse, Boolean.class, null, value);
    }

    @XmlElementDecl(namespace = VehicleEndpoint.NAMESPACE_URI, name = "commonFault")
    public JAXBElement<ErrorResponse> createCommonFault(ErrorResponse value) {
        return new JAXBElement<>(QNames.commonFault, ErrorResponse.class, null, value);
    }


}
