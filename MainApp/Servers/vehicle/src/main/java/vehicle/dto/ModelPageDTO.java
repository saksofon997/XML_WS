package vehicle.dto;

import lombok.Data;
import saga.dto.ModelDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelPageDTO {
    private List<ModelDTO> content;
    private int pageNo;
    private int totalPages;

    public ModelPageDTO(){
        content = new ArrayList<ModelDTO>();
    }
}
