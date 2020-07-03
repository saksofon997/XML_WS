package rental.dto;

import lombok.Data;

import java.util.Objects;

public class RentalReportDTO {

    private Long id;
    private Long rentalId;
    private double mileage;
    private String description;

    public RentalReportDTO(Long id, Long rentalId, double mileage, String description) {
        this.id = id;
        this.rentalId = rentalId;
        this.mileage = mileage;
        this.description = description;
    }

    public RentalReportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RentalReportDTO{" +
                "id=" + id +
                ", rentalId=" + rentalId +
                ", mileage=" + mileage +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalReportDTO that = (RentalReportDTO) o;
        return Double.compare(that.getMileage(), getMileage()) == 0 &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRentalId(), that.getRentalId()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRentalId(), getMileage(), getDescription());
    }
}
