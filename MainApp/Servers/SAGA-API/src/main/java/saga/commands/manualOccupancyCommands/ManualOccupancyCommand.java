package saga.commands.manualOccupancyCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleOccupancyDTO;

public class ManualOccupancyCommand {
    @TargetAggregateIdentifier
    private Long occupancyId;
    private VehicleOccupancyDTO occupancyDTO;
    private Long vehicleId;

    public ManualOccupancyCommand() {
    }

    public ManualOccupancyCommand(Long occupancyId, VehicleOccupancyDTO occupancyDTO, Long vehicleId) {
        this.occupancyId = occupancyId;
        this.occupancyDTO = occupancyDTO;
        this.vehicleId = vehicleId;
    }

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
