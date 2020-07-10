package agent.soap;

import agent.soap.gen.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class VehicleClient extends WebServiceGatewaySupport {

    public JAXBElement<FuelArray> getFuels() {
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getFuelsRequest"),
                        String.class, "cao");
        JAXBElement<FuelArray> response = (JAXBElement<FuelArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("Fuels: ");
        for (Fuel fuel: response.getValue().getItem()) {
            System.out.println(fuel.getName());
        }
        System.out.println(response.getValue().getItem().size());
        return response;
    }

    public Long createNewVehicle(Vehicle newVehicle) {
        JAXBElement<Vehicle> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","createNewVehicleRequest"),
                        Vehicle.class, newVehicle);
        JAXBElement<Long> response = (JAXBElement<Long>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        return response.getValue();
    }

    public JAXBElement<BrandArray> getBrands(){
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getBrandsRequest"),
                        String.class, "Daj brendove");
        JAXBElement<BrandArray> response = (JAXBElement<BrandArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("Brands: ");
//        for (BrandDTO brand: response.getValue().getItem()) {
//            System.out.println("Models for brand: " + brand.getName());
//            for (ModelDTO model: brand.getModels()) {
//                System.out.println(model.getName());
//            }
//        }
        System.out.println(response.getValue().getItem().size());
        return response;
    }

    public JAXBElement<CategoryArray> getCategories(){
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getCategoriesRequest"),
                        String.class, "Daj kategorije");
        JAXBElement<CategoryArray> response = (JAXBElement<CategoryArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("Categories: ");
        for (Category category: response.getValue().getItem()) {
            System.out.println(category.getName());
        }
        System.out.println(response.getValue().getItem().size());
        return response;
    }

    public JAXBElement<TransmissionArray> getTransmissions(){
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getTransmissionsRequest"),
                        String.class, "Daj transmisone");
        JAXBElement<TransmissionArray> response = (JAXBElement<TransmissionArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("Transmissions: ");
        for (Transmission transmission: response.getValue().getItem()) {
            System.out.println(transmission.getName());
        }
        System.out.println(response.getValue().getItem().size());
        return response;
    }

    public JAXBElement<ModelArray> getModels(Long brandId){
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getModelsRequest"),
                        Long.class, brandId);
        JAXBElement<ModelArray> response = (JAXBElement<ModelArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("Models: ");
        for (ModelDTO model: response.getValue().getItem()) {
            System.out.println(model.getName());
        }
        System.out.println(response.getValue().getItem().size());
        return response;
    }

    public JAXBElement<Boolean> addOccupancy(VehicleOccupancyDTO vehicleOccupancyDTO){
        JAXBElement<VehicleOccupancyDTO> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","createOccupancyRequest"),
                        VehicleOccupancyDTO.class, vehicleOccupancyDTO);
        JAXBElement<Boolean> response = (JAXBElement<Boolean>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println(response.getValue());
        return response;
    }
}
