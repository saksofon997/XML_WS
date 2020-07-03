package saga.commands.rentalReservedCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleOccupancyDTO;

public class RentalVehicleOccupancyAddCommand {
    @TargetAggregateIdentifier
    private String rentalReservedAggregateId;

    private Long rentalId;
    private VehicleOccupancyDTO occupancyDTO;
    private Long vehicleId;

    public RentalVehicleOccupancyAddCommand() {
    }

    public RentalVehicleOccupancyAddCommand(String rentalReservedAggregateId, Long rentalId, VehicleOccupancyDTO occupancyDTO, Long vehicleId) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
        this.rentalId = rentalId;
        this.occupancyDTO = occupancyDTO;
        this.vehicleId = vehicleId;
    }

    public String getRentalReservedAggregateId() {
        return rentalReservedAggregateId;
    }

    public void setRentalReservedAggregateId(String rentalReservedAggregateId) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
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
