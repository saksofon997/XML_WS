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

    public List<VehicleDTO> getContent() {
        return content;
    }

    public void setContent(List<VehicleDTO> content) {
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
