package saga.events.modelEvents;

import saga.commands.TypeOfCommand;

public class ModelMainFailedEvent {
    private String modelAggregateId;
    private Long modelId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public ModelMainFailedEvent() {
    }

    public ModelMainFailedEvent(String modelAggregateId, Long modelId, String reason, TypeOfCommand typeOfCommand) {
        this.modelAggregateId = modelAggregateId;
        this.modelId = modelId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getModelAggregateId() {
        return modelAggregateId;
    }

    public void setModelAggregateId(String modelAggregateId) {
        this.modelAggregateId = modelAggregateId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
