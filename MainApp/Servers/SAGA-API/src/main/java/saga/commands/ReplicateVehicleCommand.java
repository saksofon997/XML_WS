package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleDTO;

public class ReplicateVehicleCommand {
    @TargetAggregateIdentifier
    private String vehicleAggregateId;

    private Long vehicleId;
    private VehicleDTO vehicleDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateVehicleCommand() {
    }

    public ReplicateVehicleCommand(String vehicleAggregateId, Long vehicleId, VehicleDTO vehicleDTO, TypeOfCommand typeOfCommand) {
        this.vehicleAggregateId = vehicleAggregateId;
        this.vehicleId = vehicleId;
        this.vehicleDTO = vehicleDTO;
        this.typeOfCommand = typeOfCommand;
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

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
