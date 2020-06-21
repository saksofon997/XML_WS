package vehicle.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import vehicle.exceptions.ConversionFailedError;
import vehicle.service.*;
import vehicle.soap.arrays.*;
import vehicle.model.Vehicle;

import javax.xml.bind.JAXBElement;

@Endpoint
public class VehicleEndpoint implements WSEndpoint{

    FuelService fuelService;
    BrandService brandService;
    ModelService modelService;
    CategoryService categoryService;
    TransmissionService transmissionService;
    VehicleService vehicleService;
    private ObjectFactory objectFactory;

    @Autowired
    public VehicleEndpoint(FuelService fuelService,
                           TransmissionService transmissionService,
                           VehicleService vehicleService,
                           BrandService brandService,
                           CategoryService categoryService,
                           ModelService modelService) {
        this.fuelService = fuelService;
        this.objectFactory = new ObjectFactory();
        this.transmissionService = transmissionService;
        this.vehicleService = vehicleService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.modelService = modelService;
    }

    // Fuel: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFuelsRequest")
    @ResponsePayload
    public JAXBElement<FuelArray> getFuels(@RequestPayload JAXBElement<String> string) {
        FuelArray fuels = fuelService.getAllSOAP();
        return objectFactory.createGetFuelsResponse(fuels);
    }

    // Brand: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBrandsRequest")
    @ResponsePayload
    public JAXBElement<BrandArray> getBrands(@RequestPayload JAXBElement<String> string) {
        BrandArray brands = brandService.getAllSOAP();
        System.out.println("Endpoint brands");
        return objectFactory.createGetBrandsResponse(brands);
    }

    // Category: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoriesRequest")
    @ResponsePayload
    public JAXBElement<CategoryArray> getCategories(@RequestPayload JAXBElement<String> string) {
        CategoryArray categories = categoryService.getAllSOAP();
        System.out.println("Endpoint categories");
        return objectFactory.createGetCategoriesResponse(categories);
    }

    // Transmission: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransmissionsRequest")
    @ResponsePayload
    public JAXBElement<TransmissionArray> getTransmissions(@RequestPayload JAXBElement<String> string) {
        System.out.println("Endpoint transmissions");
        TransmissionArray transmissions = transmissionService.getAllSOAP();
        return objectFactory.createGetTransmissionsResponse(transmissions);
    }

    // Model: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getModelsRequest")
    @ResponsePayload
    public JAXBElement<ModelArray> getModels(@RequestPayload JAXBElement<Long> brandId) {
        ModelArray models = modelService.getAllSOAP(brandId.getValue());
        System.out.println("Endpoint models");
        return objectFactory.createGetModelsResponse(models);
    }

    // Vehicle: get all
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVehiclesRequest")
    @ResponsePayload
    public JAXBElement<VehicleArray> getVehicles(@RequestPayload JAXBElement<String> string) {
        VehicleArray vehicles = vehicleService.getAllSOAP();
        return objectFactory.createGetVehiclesResponse(vehicles);
    }

    // Vehicle: get one
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneVehicleRequest")
    @ResponsePayload
    public JAXBElement<Vehicle> getOneVehicle(@RequestPayload JAXBElement<Long> value) {
        Vehicle vehicle = vehicleService.getOneSOAP(value.getValue());
        return objectFactory.createGetOneVehicleResponse(vehicle);
    }

    // Vehicle: update one
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOneVehicleRequest")
    @ResponsePayload
    public JAXBElement<Vehicle> updateOneVehicle(@RequestPayload JAXBElement<Vehicle> value) {
        Vehicle vehicle = vehicleService.updateOneSOAP(value.getValue().getId(), value.getValue());
        return objectFactory.CreateUpdateOneVehicleResponse(vehicle);
    }

    // Vehicle: add new vehicle
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
