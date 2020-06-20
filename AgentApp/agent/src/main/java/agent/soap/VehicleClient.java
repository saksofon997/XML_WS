package agent.soap;

import agent.soap.gen.Fuel;
import agent.soap.gen.FuelArray;
import agent.soap.gen.Vehicle;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class VehicleClient extends WebServiceGatewaySupport {

    public JAXBElement<FuelArray> getFuels() {
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/vehicle","getFuelsRequest"),
                        String.class, "cao");
        JAXBElement<FuelArray> response = (JAXBElement<FuelArray>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
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
}
