package vehicle.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryPageDTO {
    private List<CategoryDTO> content;
    private int pageNo;
    private int totalPages;

    public CategoryPageDTO(){
        content = new ArrayList<CategoryDTO>();
    }
}
