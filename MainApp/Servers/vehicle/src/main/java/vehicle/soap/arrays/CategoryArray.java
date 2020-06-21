package vehicle.soap.arrays;

import vehicle.model.Category;
import vehicle.soap.WSEndpoint;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryArray", propOrder = {
        "item"
})
public class CategoryArray {
    @XmlElement
    @XmlSchemaType(name = "category", namespace = WSEndpoint.NAMESPACE_URI)
    List<Category> item;

    public List<Category> getItem(){
        if(this.item == null){
            item = new ArrayList<Category>();
        }
        return this.item;
    }
}
