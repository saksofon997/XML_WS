package search.dto;

import lombok.Data;
import saga.dto.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResultPageDTO {

    private List<VehicleDTO> content;
    private int pageNo;
    private int totalPages;

    public SearchResultPageDTO() {
        content = new ArrayList<VehicleDTO>();
    }

}
