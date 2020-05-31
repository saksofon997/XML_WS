package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleDTO;

public class CreateVehicleCommand {
    @TargetAggregateIdentifier
    private Long vehicleId;
    private VehicleDTO vehicleDTO;

    public CreateVehicleCommand() {
    }

    public CreateVehicleCommand(Long vehicleId, VehicleDTO vehicleDTO) {
        this.vehicleId = vehicleId;
        this.vehicleDTO = vehicleDTO;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }
}
