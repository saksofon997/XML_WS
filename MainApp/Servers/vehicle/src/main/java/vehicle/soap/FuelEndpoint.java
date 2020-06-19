package vehicle.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import saga.dto.FuelDTO;
import saga.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Fuel;
import vehicle.model.ObjectFactory;
import vehicle.service.FuelService;
import vehicle.service.TransmissionService;

import javax.xml.bind.JAXBElement;


@Endpoint
public class FuelEndpoint {
    public static final String NAMESPACE_URI = "http://www.vehicle.com/fuel";

    FuelService fuelService;
    TransmissionService transmissionService;
    private ObjectFactory objectFactory;

    @Autowired
    public FuelEndpoint(FuelService fuelService, TransmissionService transmissionService) {
        this.fuelService = fuelService;
        this.objectFactory = new ObjectFactory();
        this.transmissionService = transmissionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFuelNameRequest")
    @ResponsePayload
    public JAXBElement<Fuel> getFuelName(@RequestPayload JAXBElement<String> string) {
        FuelDTO fuel = null;
        try {
            fuel = fuelService.getOne(1L);
        } catch (EntityNotFound entityNotFound) {
            entityNotFound.printStackTrace();
        } catch (ConversionFailedError conversionFailedError) {
            conversionFailedError.printStackTrace();
        }
        Fuel f = new Fuel();
        f.setName("ELEKTRIKA");
        f.setId(2L);
        return objectFactory.createGetFuelResponse(f);
    }
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransmissionRequest")
//    @ResponsePayload
//    public JAXBElement<TransmissionDTO> getBrandName() {
//        TransmissionDTO transmissionDTO = null;
//        try {
//            transmissionDTO = transmissionService.getOne(1L);
//        } catch (EntityNotFound | ConversionFailedError entityNotFound) {
//            entityNotFound.printStackTrace();
//        }
//        return objectFactory.createGetTransmissionResponse(transmissionDTO);
//    }

}
