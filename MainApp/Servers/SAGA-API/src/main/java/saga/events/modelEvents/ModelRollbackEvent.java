package saga.events.modelEvents;

import saga.commands.TypeOfCommand;

public class ModelRollbackEvent {
    private Long brandId;
    private Long modelId;
    private TypeOfCommand typeOfCommand;

    public ModelRollbackEvent() {
    }

    public ModelRollbackEvent(Long brandId, Long modelId, TypeOfCommand typeOfCommand) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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
