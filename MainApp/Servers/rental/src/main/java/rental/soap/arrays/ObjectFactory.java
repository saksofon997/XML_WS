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
        // Rental : update
        QName createUpdateRentalRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createUpdateRentalRequest");
        QName createUpdateRentalResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createUpdateRentalResponse");

        // RentalReport : create new
        QName createNewRentalReportRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalReportRequest");
        QName createNewRentalReportResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewRentalReportResponse");
        // Bundle : create new
        QName createNewBundleRequest = new QName(RentalEndpoint.NAMESPACE_URI, "createNewBundleRequest");
        QName createNewBundleResponse = new QName(RentalEndpoint.NAMESPACE_URI, "createNewBundleResponse");

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

    // Rental : add One
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createUpdateRentalRequest")
    public JAXBElement<RentalDTO> createUpdateRentalRequest(RentalDTO value) {
        return new JAXBElement<>(QNames.createUpdateRentalRequest, RentalDTO.class, null, value);
    }
    @XmlElementDecl(namespace = RentalEndpoint.NAMESPACE_URI, name = "createUpdateRentalResponse")
    public JAXBElement<RentalDTO> createUpdateRentalResponse(RentalDTO value) {
        return new JAXBElement<>(QNames.createUpdateRentalResponse, RentalDTO.class, null, value);
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
