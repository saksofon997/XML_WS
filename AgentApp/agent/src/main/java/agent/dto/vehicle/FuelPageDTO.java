package agent.dto.vehicle;

import agent.dto.shared.FuelDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FuelPageDTO {
    private List<FuelDTO> content;
    private int pageNo;
    private int totalPages;

    public FuelPageDTO(){
        content = new ArrayList<FuelDTO>();
    }
}
