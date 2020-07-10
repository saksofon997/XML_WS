package agent.soap;

import agent.soap.gen.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class RentalClient extends WebServiceGatewaySupport {
    @Value("${company}")
    private String cid;

    public JAXBElement<BundleDTO> addBundle(BundleDTO brandDTO){
        JAXBElement<BundleDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/rental","createNewBundleRequest"),
                        BundleDTO.class, brandDTO);
        JAXBElement<BundleDTO> response = (JAXBElement<BundleDTO>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("BundleDTO: " + response.getValue().getId() + " " + response.getValue().getName());
        return response;
    }

    public JAXBElement<RentalDTO> addRental(RentalDTO rentalDTO){
        rentalDTO.setCid(this.cid);
        JAXBElement<RentalDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/rental","createNewRentalRequest"),
                        RentalDTO.class, rentalDTO);
        JAXBElement<RentalDTO> response = (JAXBElement<RentalDTO>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("RentalDTO: " + response.getValue().getId() + " Owner: " + response.getValue().getOwnerId());
        return response;
    }

    public JAXBElement<RentalDTO> updateRental(RentalDTO rentalDTO){
        JAXBElement<RentalDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/rental","createUpdateRentalRequest"),
                        RentalDTO.class, rentalDTO);
        JAXBElement<RentalDTO> response = (JAXBElement<RentalDTO>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("RentalDTO: " + response.getValue().getId() + " Owner: " + response.getValue().getOwnerId());
        return response;
    }
}
