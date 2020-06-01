package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollBackFuelCommand {
    @TargetAggregateIdentifier
    private Long fuelId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackFuelCommand() {}

    public RollBackFuelCommand(Long fuelId, String status, TypeOfCommand typeOfCommand) {
        this.fuelId = fuelId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
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
