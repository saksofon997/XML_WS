package vehicle.soap.arrays;

import vehicle.model.Transmission;
import vehicle.soap.WSEndpoint;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmissionArray", propOrder = {
        "item"
})
public class TransmissionArray {
    @XmlElement
    @XmlSchemaType(name = "transmission", namespace = WSEndpoint.NAMESPACE_URI)
    List<Transmission> item;

    public List<Transmission> getItem(){
        if(this.item == null) {
            item = new ArrayList<Transmission>();
        }
        return this.item;
    }
}
