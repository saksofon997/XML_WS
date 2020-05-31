package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleDTO;

public class ReplicateVehicleCommand {
    @TargetAggregateIdentifier
    private String vehicleAggregateId;

    private Long vehicleId;
    private VehicleDTO vehicleDTO;

    public ReplicateVehicleCommand() {
    }

    public ReplicateVehicleCommand(String vehicleAggregateId, Long vehicleId, VehicleDTO vehicleDTO) {
        this.vehicleAggregateId = vehicleAggregateId;
        this.vehicleId = vehicleId;
        this.vehicleDTO = vehicleDTO;
    }

    public String getVehicleAggregateId() {
        return vehicleAggregateId;
    }

    public void setVehicleAggregateId(String vehicleAggregateId) {
        this.vehicleAggregateId = vehicleAggregateId;
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
