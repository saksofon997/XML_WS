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

    public List<BrandDTO> getContent() {
        return content;
    }

    public void setContent(List<BrandDTO> content) {
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
