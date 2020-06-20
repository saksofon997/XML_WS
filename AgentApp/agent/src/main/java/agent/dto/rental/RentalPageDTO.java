package agent.dto.rental;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RentalPageDTO {

    private List<RentalDTO> content = new ArrayList<>();
    private int pageNo;
    private int totalPages;
}
