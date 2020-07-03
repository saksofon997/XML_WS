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
           savedRental = rentalService.add(rentalDTO);
        } catch (ConversionFailedError conversionFailedError) {
            conversionFailedError.printStackTrace();
            RentalDTO errorDTO = new RentalDTO();
            errorDTO.setId(-1L);
            return objectFactory.createNewRentalResponse(errorDTO);
        } catch (EntityNotFound | DuplicateEntity exception) {
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
//    // Fuel: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFuelsRequest")
//    @ResponsePayload
//    public JAXBElement<FuelArray> getFuels(@RequestPayload JAXBElement<String> string) {
//        FuelArray fuels = fuelService.getAllSOAP();
//        return objectFactory.createGetFuelsResponse(fuels);
//    }
//
//    // Brand: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBrandsRequest")
//    @ResponsePayload
//    public JAXBElement<BrandArray> getBrands(@RequestPayload JAXBElement<String> string) {
//        BrandArray brands = brandService.getAllSOAP();
//        System.out.println("Endpoint brands");
//        return objectFactory.createGetBrandsResponse(brands);
//    }
//
//    // Category: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoriesRequest")
//    @ResponsePayload
//    public JAXBElement<CategoryArray> getCategories(@RequestPayload JAXBElement<String> string) {
//        CategoryArray categories = categoryService.getAllSOAP();
//        System.out.println("Endpoint categories");
//        return objectFactory.createGetCategoriesResponse(categories);
//    }
//
//    // Transmission: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransmissionsRequest")
//    @ResponsePayload
//    public JAXBElement<TransmissionArray> getTransmissions(@RequestPayload JAXBElement<String> string) {
//        System.out.println("Endpoint transmissions");
//        TransmissionArray transmissions = transmissionService.getAllSOAP();
//        return objectFactory.createGetTransmissionsResponse(transmissions);
//    }
//
//    // Model: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getModelsRequest")
//    @ResponsePayload
//    public JAXBElement<ModelArray> getModels(@RequestPayload JAXBElement<Long> brandId) {
//        ModelArray models = modelService.getAllSOAP(brandId.getValue());
//        System.out.println("Endpoint models");
//        return objectFactory.createGetModelsResponse(models);
//    }
//
//    // Vehicle: get all
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVehiclesRequest")
//    @ResponsePayload
//    public JAXBElement<VehicleArray> getVehicles(@RequestPayload JAXBElement<String> string) {
//        VehicleArray vehicles = vehicleService.getAllSOAP();
//        return objectFactory.createGetVehiclesResponse(vehicles);
//    }
//
//    // Vehicle: get one
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneVehicleRequest")
//    @ResponsePayload
//    public JAXBElement<Vehicle> getOneVehicle(@RequestPayload JAXBElement<Long> value) {
//        Vehicle vehicle = vehicleService.getOneSOAP(value.getValue());
//        return objectFactory.createGetOneVehicleResponse(vehicle);
//    }
//
//    // Vehicle: update one
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOneVehicleRequest")
//    @ResponsePayload
//    public JAXBElement<Vehicle> updateOneVehicle(@RequestPayload JAXBElement<Vehicle> value) {
//        Vehicle vehicle = vehicleService.updateOneSOAP(value.getValue().getId(), value.getValue());
//        return objectFactory.CreateUpdateOneVehicleResponse(vehicle);
//    }
//


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
