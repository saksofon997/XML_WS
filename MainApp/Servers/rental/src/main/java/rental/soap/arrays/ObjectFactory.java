package rental.soap.arrays;

import rental.dto.BundleDTO;
import rental.dto.RentalDTO;
import rental.model.Rental;
import rental.soap.RentalEndpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        // Rental : create new
        QName createNewRentalRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalRequest");
        QName createNewRentalResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalResponse");
        // RentalReport : create new
        QName createNewRentalReportRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalReportRequest");
        QName createNewRentalReportResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalReportResponse");
        // Bundle : create new
        QName createNewBundleRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewBundleRequest");
        QName createNewBundleResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewBundleResponse");
//        // Category : get All
//        QName getCategoriesRequest = new QName(RentalEndpoint.NAMESPACE_URI, "getCategoriesRequest");
//        QName getCategoriesResponse = new QName(RentalEndpoint.NAMESPACE_URI, "getCategoriesResponse");
//        // Transmission : get All
//        QName getTransmissionsRequest = new QName(RentalEndpoint.NAMESPACE_URI, "getTransmissionsRequest");
//        QName getTransmissionsResponse = new QName(RentalEndpoint.NAMESPACE_URI, "getTransmissionsResponse");
//        // Model : get All
//        QName getModelsRequest = new QName(RentalEndpoint.NAMESPACE_URI, "getModelsRequest");
//        QName getModelsResponse = new QName(RentalEndpoint.NAMESPACE_URI, "getModelsResponse");
//        // Vehicle : get All
//        QName getVehiclesRequest = new QName(RentalEndpoint.NAMESPACE_URI, "getVehiclesRequest");
//        QName getVehiclesResponse = new QName(RentalEndpoint.NAMESPACE_URI, "getVehiclesResponse");
//        // Vehicle : get One
//        QName getOneVehicleRequest = new QName(RentalEndpoint.NAMESPACE_URI, "getOneVehicleRequest");
//        QName getOneVehicleResponse = new QName(RentalEndpoint.NAMESPACE_URI, "getOneVehicleResponse");
//        // Vehicle : update One
//        QName updateOneVehicleRequest = new QName(RentalEndpoint.NAMESPACE_URI, "updateOneVehicleRequest");
//        QName updateOneVehicleResponse = new QName(RentalEndpoint.NAMESPACE_URI, "updateOneVehicleResponse");
//        // Vehicle : add One
//        QName createNewVehicleRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewVehicleRequest");
//        QName createNewVehicleResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewVehicleResponse");

        QName rentalcommonFault = new QName(RentalEndpoint.NAMESPACE_URI, "rentalcommonFault");
    }
    // Rental : add One
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createNewRentalRequest")
    public JAXBElement<RentalDTO> createNewRentalRequest(RentalDTO value) {
        return new JAXBElement<>(QNames.createNewRentalRequest, RentalDTO.class, null, value);
    }
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createNewRentalResponse")
    public JAXBElement<RentalDTO> createNewRentalResponse(RentalDTO value) {
        return new JAXBElement<>(QNames.createNewRentalResponse, RentalDTO.class, null, value);
    }

    // Bundle : add One
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createNewBundleRequest")
    public JAXBElement<BundleDTO> createNewBundleRequest(BundleDTO value) {
        return new JAXBElement<>(QNames.createNewBundleRequest, BundleDTO.class, null, value);
    }
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createNewBundleResponse")
    public JAXBElement<BundleDTO> createNewBundleResponse(BundleDTO value) {
        return new JAXBElement<>(QNames.createNewBundleResponse, BundleDTO.class, null, value);
    }

    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "rentalcommonFault")
    public JAXBElement<ErrorResponse> createCommonFault(ErrorResponse value) {
        return new JAXBElement<>(QNames.rentalcommonFault, ErrorResponse.class, null, value);
    }


}
