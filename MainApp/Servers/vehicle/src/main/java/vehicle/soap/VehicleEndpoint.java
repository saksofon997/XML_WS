package vehicle.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import vehicle.exceptions.ConversionFailedError;
import vehicle.model.ObjectFactory;
import vehicle.model.Vehicle;
import vehicle.service.FuelService;
import vehicle.service.TransmissionService;
import vehicle.service.VehicleService;
import vehicle.model.FuelArray;

import javax.xml.bind.JAXBElement;

@Endpoint
public class VehicleEndpoint implements WSEndpoint{

    FuelService fuelService;
    TransmissionService transmissionService;
    VehicleService vehicleService;
    private ObjectFactory objectFactory;

    @Autowired
    public VehicleEndpoint(FuelService fuelService, TransmissionService transmissionService, VehicleService vehicleService) {
        this.fuelService = fuelService;
        this.objectFactory = new ObjectFactory();
        this.transmissionService = transmissionService;
        this.vehicleService = vehicleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFuelsRequest")
    @ResponsePayload
    public JAXBElement<FuelArray> getFuels(@RequestPayload JAXBElement<String> string) {
        FuelArray fuels = fuelService.getAllSOAP();
        return objectFactory.createGetFuelsResponse(fuels);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewVehicleRequest")
    @ResponsePayload
    public JAXBElement<Long> createNewVehicle(@RequestPayload JAXBElement<Vehicle> vehicle) {
        Vehicle vehicleReal = vehicle.getValue();
        vehicleReal.setId(null);
        Long savedVehicle = null;
        try {
           savedVehicle = vehicleService.addViaSoap(vehicleReal);
        } catch (ConversionFailedError conversionFailedError) {
            conversionFailedError.printStackTrace();
            return objectFactory.createNewVehicleResponse(-1L);
        }
        return objectFactory.createNewVehicleResponse(savedVehicle);
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
