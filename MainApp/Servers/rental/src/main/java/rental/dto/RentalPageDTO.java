package rental.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentalPageDTO {

    private List<RentalDTO> content = new ArrayList<>();
    private int pageNo;
    private int totalPages;

    public RentalPageDTO(List<RentalDTO> content, int pageNo, int totalPages) {
        this.content = content;
        this.pageNo = pageNo;
        this.totalPages = totalPages;
    }

    public RentalPageDTO() {
    }

    public List<RentalDTO> getContent() {
        return content;
    }

    public void setContent(List<RentalDTO> content) {
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

    @Override
    public String toString() {
        return "RentalPageDTO{" +
                "content=" + content +
                ", pageNo=" + pageNo +
                ", totalPages=" + totalPages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalPageDTO that = (RentalPageDTO) o;
        return getPageNo() == that.getPageNo() &&
                getTotalPages() == that.getTotalPages() &&
                Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getPageNo(), getTotalPages());
    }
}
