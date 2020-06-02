package saga.events.modelEvents;

import saga.commands.TypeOfCommand;

public class ModelRollbackEvent {
    private Long modelId;
    private TypeOfCommand typeOfCommand;

    public ModelRollbackEvent() {
    }

    public ModelRollbackEvent(Long modelId, TypeOfCommand typeOfCommand) {
        this.modelId = modelId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
