package rental.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rental.dto.BundleDTO;
import rental.dto.RentalDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.service.BundleService;
import rental.service.RentalService;
import rental.soap.arrays.ObjectFactory;

import javax.xml.bind.JAXBElement;

@Endpoint
public class RentalEndpoint implements WSEndpoint{

    private ObjectFactory objectFactory;
    private RentalService rentalService;
    private BundleService bundleService;

    @Autowired
    public RentalEndpoint(RentalService rentalService,
                          BundleService bundleService) {
        this.rentalService = rentalService;
        this.bundleService = bundleService;
        this.objectFactory = new ObjectFactory();
    }

    // Rental: add new rental
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewRentalRequest")
    @ResponsePayload
    public JAXBElement<RentalDTO> createNewRental(@RequestPayload JAXBElement<RentalDTO> rental) {
        RentalDTO rentalDTO = rental.getValue();
        rentalDTO.setId(null);
        RentalDTO savedRental = null;
        try {
           savedRental = rentalService.addViaSOAP(rentalDTO);
        } catch (ConversionFailedError conversionFailedError) {
            conversionFailedError.printStackTrace();
            RentalDTO errorDTO = new RentalDTO();
            errorDTO.setId(-1L);
            return objectFactory.createNewRentalResponse(errorDTO);
        } catch (EntityNotFound exception) {
            exception.printStackTrace();
        }
        return objectFactory.createNewRentalResponse(savedRental);
    }

    // Bundle: add new bundle
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewBundleRequest")
    @ResponsePayload
    public JAXBElement<BundleDTO> createNewBundle(@RequestPayload JAXBElement<BundleDTO> bundle) {
        BundleDTO bundleDTO = bundle.getValue();
        bundleDTO.setId(null);
        BundleDTO savedBundle = null;
        try {
            savedBundle = bundleService.add(bundleDTO);
        } catch (ConversionFailedError | DuplicateEntity exception) {
            exception.printStackTrace();
            BundleDTO errorDTO = new BundleDTO();
            errorDTO.setId(-1L);
            return objectFactory.createNewBundleResponse(errorDTO);
        }
        return objectFactory.createNewBundleResponse(savedBundle);
    }


}
