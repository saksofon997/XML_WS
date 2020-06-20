package agent.soap;

import agent.soap.gen.Fuel;
import agent.soap.gen.Vehicle;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class VehicleClient extends WebServiceGatewaySupport {

    public JAXBElement<Fuel> getFuelName() {
        JAXBElement<String> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/fuel","getFuelNameRequest"),
                        String.class, "cao");
        JAXBElement<Fuel> response = (JAXBElement<Fuel>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        return response;
    }

    public Long createNewVehicle(Vehicle newVehicle) {
        JAXBElement<Vehicle> jaxbElement =
                new JAXBElement(new QName("http://www.vehicle.com/fuel","createNewVehicleRequest"),
                        Vehicle.class, newVehicle);
        JAXBElement<Long> response = (JAXBElement<Long>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        return response.getValue();
    }
}
