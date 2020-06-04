package saga.events.modelEvents;

import saga.commands.TypeOfCommand;

public class ModelReplicatedEvent {
    private String modelAggregateId;
    private TypeOfCommand typeOfCommand;

    public ModelReplicatedEvent() {
    }

    public ModelReplicatedEvent(String modelAggregateId, TypeOfCommand typeOfCommand) {
        this.modelAggregateId = modelAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getModelAggregateId() {
        return modelAggregateId;
    }

    public void setModelAggregateId(String modelAggregateId) {
        this.modelAggregateId = modelAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
