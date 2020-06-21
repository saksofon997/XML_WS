package vehicle.soap.arrays;

import vehicle.soap.WSEndpoint;
import vehicle.soap.dtos.ModelDTO;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modelArray", propOrder = {
        "item"
})
public class ModelArray {
    @XmlElement
    @XmlSchemaType(name = "modelDTO", namespace = WSEndpoint.NAMESPACE_URI)
    List<ModelDTO> item;

    public List<ModelDTO> getItem(){
        if(this.item == null) {
            item = new ArrayList<ModelDTO>();
        }
        return this.item;
    }
}
