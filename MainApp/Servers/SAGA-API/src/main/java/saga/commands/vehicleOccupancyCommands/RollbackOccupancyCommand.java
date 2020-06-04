package saga.commands.vehicleOccupancyCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollbackOccupancyCommand {
    @TargetAggregateIdentifier
    private Long vehicleOccupancyId;
    private Long vehicleId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollbackOccupancyCommand() {
    }

    public RollbackOccupancyCommand(Long vehicleOccupancyId, Long vehicleId, String status, TypeOfCommand typeOfCommand) {
        this.vehicleOccupancyId = vehicleOccupancyId;
        this.vehicleId = vehicleId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleOccupancyId() {
        return vehicleOccupancyId;
    }

    public void setVehicleOccupancyId(Long vehicleOccupancyId) {
        this.vehicleOccupancyId = vehicleOccupancyId;
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
