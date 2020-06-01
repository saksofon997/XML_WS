package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollbackVehicleCommand {
    @TargetAggregateIdentifier
    private Long vehicleId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollbackVehicleCommand() {
    }

    public RollbackVehicleCommand(Long vehicleId, String status, TypeOfCommand typeOfCommand) {
        this.vehicleId = vehicleId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
