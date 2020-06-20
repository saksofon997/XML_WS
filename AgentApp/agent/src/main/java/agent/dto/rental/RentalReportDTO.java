package agent.dto.rental;


import lombok.Data;

@Data
public class RentalReportDTO {

    private Long id;
    private Long rentalId;
    private double mileage;
    private String description;
}
