package saga.commands.rentalReservedCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleOccupancyDTO;

public class RentalReservedCommand {
    @TargetAggregateIdentifier
    private Long rentalId;
    private VehicleOccupancyDTO occupancyDTO;
    private Long vehicleId;

    public RentalReservedCommand() {
    }

    public RentalReservedCommand(Long rentalId, VehicleOccupancyDTO occupancyDTO, Long vehicleId) {
        this.rentalId = rentalId;
        this.occupancyDTO = occupancyDTO;
        this.vehicleId = vehicleId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public VehicleOccupancyDTO getOccupancyDTO() {
        return occupancyDTO;
    }

    public void setOccupancyDTO(VehicleOccupancyDTO occupancyDTO) {
        this.occupancyDTO = occupancyDTO;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
