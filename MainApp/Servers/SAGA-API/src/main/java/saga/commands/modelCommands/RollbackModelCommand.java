package saga.commands.modelCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollbackModelCommand {
    @TargetAggregateIdentifier
    private Long modelId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollbackModelCommand() {
    }

    public RollbackModelCommand(Long modelId, String status, TypeOfCommand typeOfCommand) {
        this.modelId = modelId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
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
