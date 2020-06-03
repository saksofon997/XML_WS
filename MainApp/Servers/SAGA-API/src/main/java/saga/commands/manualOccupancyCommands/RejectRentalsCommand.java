package saga.commands.manualOccupancyCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleOccupancyDTO;

public class RejectRentalsCommand {
    @TargetAggregateIdentifier
    private String  occupancyAggregateId;

    private Long occupancyId;
    private VehicleOccupancyDTO occupancyDTO;
    private Long vehicleId;

    public RejectRentalsCommand() {
    }

    public RejectRentalsCommand(String occupancyAggregateId, Long occupancyId, VehicleOccupancyDTO occupancyDTO, Long vehicleId) {
        this.occupancyAggregateId = occupancyAggregateId;
        this.occupancyId = occupancyId;
        this.occupancyDTO = occupancyDTO;
        this.vehicleId = vehicleId;
    }

    public String getOccupancyAggregateId() { return occupancyAggregateId; }

    public void setOccupancyAggregateId(String occupancyAggregateId) { this.occupancyAggregateId = occupancyAggregateId; }

    public Long getOccupancyId() {
        return occupancyId;
    }

    public void setOccupancyId(Long occupancyId) {
        this.occupancyId = occupancyId;
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
