package vehicle.dto;

import lombok.Data;
import saga.dto.CategoryDTO;

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

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
