package vehicle.soap.arrays;
import vehicle.model.Vehicle;
import vehicle.soap.WSEndpoint;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicleArray", propOrder = {
        "item"
})
public class VehicleArray {
    @XmlElement
    @XmlSchemaType(name = "vehicle", namespace = WSEndpoint.NAMESPACE_URI)
    private List<Vehicle> item;

    public List<Vehicle> getItem() {
        if (item == null) {
            item = new ArrayList<Vehicle>();
        }
        return this.item;
    }
}
