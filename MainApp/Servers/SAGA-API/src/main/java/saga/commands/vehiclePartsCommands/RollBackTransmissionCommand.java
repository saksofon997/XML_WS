package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollBackTransmissionCommand {
    @TargetAggregateIdentifier
    private Long transmissionId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackTransmissionCommand() {}

    public RollBackTransmissionCommand(Long transmissionId, String status, TypeOfCommand typeOfCommand) {
        this.transmissionId = transmissionId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
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
