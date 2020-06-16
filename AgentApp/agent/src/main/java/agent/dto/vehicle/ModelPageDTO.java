package agent.dto.vehicle;

import agent.dto.shared.ModelDTO;
import lombok.Data;

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

    public List<ModelDTO> getContent() {
        return content;
    }

    public void setContent(List<ModelDTO> content) {
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
