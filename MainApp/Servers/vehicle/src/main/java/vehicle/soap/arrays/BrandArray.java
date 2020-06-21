package vehicle.soap.arrays;

import vehicle.model.Brand;
import vehicle.soap.WSEndpoint;
import vehicle.soap.dtos.BrandDTO;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "brandArray", propOrder = {
        "item"
})
public class BrandArray {
    @XmlElement
    @XmlSchemaType(name = "brandDTO", namespace = WSEndpoint.NAMESPACE_URI)
    List<BrandDTO> item;

    public List<BrandDTO> getItem(){
        if (item == null){
            item = new ArrayList<BrandDTO>();
        }
        return this.item;
    }

}
