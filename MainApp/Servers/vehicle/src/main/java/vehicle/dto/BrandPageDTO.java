package vehicle.dto;

import lombok.Data;
import saga.dto.BrandDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandPageDTO {

    private List<BrandDTO> content;
    private int pageNo;
    private int totalPages;

    public BrandPageDTO(){
        content = new ArrayList<BrandDTO>();
    }
}
