package rental.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rental.model.RentalStatus;

@Data
@NoArgsConstructor
public class RentalDTO {

    private Long id;
    private Long vehicleId;
    private Long customerId;
    private Long ownerId;
    private long startTime;
    private long endTime;
    private BundleDTO bundle;
    private RentalStatus status;
}
