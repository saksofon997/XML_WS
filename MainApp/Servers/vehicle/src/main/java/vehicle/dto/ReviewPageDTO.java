package vehicle.dto;

import saga.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

public class ReviewPageDTO {
    private List<ReviewDTO> content;
    private int pageNo;
    private int totalPages;

    public ReviewPageDTO(){
        content = new ArrayList<ReviewDTO>();
    }

    public List<ReviewDTO> getContent() {
        return content;
    }

    public void setContent(List<ReviewDTO> content) {
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
